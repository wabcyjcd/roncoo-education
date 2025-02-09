package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.auth.req.AuthUserStudyReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * API-课程用户学习日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiUserStudyBiz extends BaseBiz {

    @NotNull
    private final UserStudyDao dao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final CacheRedis cacheRedis;

    public Result<String> study(AuthUserStudyReq req) {
        // 资源信息
        Resource resource = getByResource(req);
        if (ObjectUtil.isEmpty(resource)) {
            Result.error("resourceId不正确");
        }
        if (new BigDecimal(resource.getVideoLength()).subtract(req.getCurrentDuration()).intValue() < 1) {
            // 若视频时长-观看时长<1s,则认为观看完成
            UserStudy userStudy = getUserStudy(req);
            if (ObjectUtil.isEmpty(userStudy)) {
                Result.error("studyId不正确");
            }
            if (userStudy.getProgress().compareTo(BigDecimal.valueOf(100)) < 0) {
                // 更新进度
                userStudy.setProgress(BigDecimal.valueOf(100));
            }
            // 更新观看记录
            dao.updateById(userStudy);
            // 清空缓存
            cacheRedis.delete(Constants.RedisPre.USER_STUDY + req.getStudyId());
            cacheRedis.delete(Constants.RedisPre.PROGRESS + req.getStudyId());
            return Result.success("OK");
        }
        // 没观看完成，进度存入redis，如没看完，定时任务处理
        req.setTotalDuration(new BigDecimal(resource.getVideoLength()));
        cacheRedis.set(Constants.RedisPre.PROGRESS + req.getStudyId(), req, 1, TimeUnit.DAYS);
        return Result.success("学习中");
    }

    private Resource getByResource(AuthUserStudyReq req) {
        Resource resource = cacheRedis.getByJson(Constants.RedisPre.RESOURCE + req.getResourceId(), Resource.class);
        if (ObjectUtil.isEmpty(resource)) {
            resource = resourceDao.getById(req.getResourceId());
            cacheRedis.set(Constants.RedisPre.RESOURCE + req.getResourceId(), resource, 1, TimeUnit.HOURS);
        }
        return resource;
    }

    private UserStudy getUserStudy(AuthUserStudyReq req) {
        UserStudy userStudy = cacheRedis.getByJson(Constants.RedisPre.USER_STUDY + req.getStudyId(), UserStudy.class);
        if (ObjectUtil.isEmpty(userStudy)) {
            userStudy = dao.getById(req.getStudyId());
            cacheRedis.set(Constants.RedisPre.USER_STUDY + req.getStudyId(), userStudy, 1, TimeUnit.HOURS);
        }
        return userStudy;
    }
}
