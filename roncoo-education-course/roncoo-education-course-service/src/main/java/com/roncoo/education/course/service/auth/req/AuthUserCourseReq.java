package com.roncoo.education.course.service.auth.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-课程用户关联表
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AuthUserCourseReq", description = "API-AUTH-课程用户关联表")
public class AuthUserCourseReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;
}
