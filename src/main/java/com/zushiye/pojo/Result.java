package com.zushiye.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.util.Date;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zushiye
 * @since 2022-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Result对象", description="")
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "研究id")
    @TableId(value = "research_id", type = IdType.ID_WORKER_STR)
    private String researchId;

    @ApiModelProperty(value = "版本")
    private Integer version;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "是否发布 0 未发布 1 发布")
    private Integer isIssue;

    @ApiModelProperty(value = "逻辑删除")
    @TableLogic
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    @ApiModelProperty(value = "修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


}
