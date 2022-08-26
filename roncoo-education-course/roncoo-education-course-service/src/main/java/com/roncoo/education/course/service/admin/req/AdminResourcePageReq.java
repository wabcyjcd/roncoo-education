package com.roncoo.education.course.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-课程视频信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "AdminResourcePageReq", description = "ADMIN-课程视频信息分页")
public class AdminResourcePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "视频名称")
    private String recourseName;

    @ApiModelProperty(value = "文件大小")
    private Long recourseSize;

    @ApiModelProperty(value = "资源类型(1:视频;2:文件)")
    private Integer recourseType;

    @ApiModelProperty(value = "视频状态(1待上传，2上传成功，3上传失败)")
    private Integer videoStatus;

    @ApiModelProperty(value = "时长")
    private String videoLength;

    @ApiModelProperty(value = "视频ID")
    private String videoVid;

    @ApiModelProperty(value = "文件地址")
    private String fileUrl;

    @ApiModelProperty(value = "文件名称")
    private String fileName;

    @ApiModelProperty(value = "当前页")
    private int pageCurrent = 1;

    @ApiModelProperty(value = "每页条数")
    private int pageSize = 20;
}
