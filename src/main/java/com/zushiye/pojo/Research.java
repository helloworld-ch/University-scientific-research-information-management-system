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
@ApiModel(value="Research对象", description="")
public class Research implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "研究id")
    @TableId(value = "research_id", type = IdType.ID_WORKER_STR)
    private String researchId;

    @ApiModelProperty(value = "研究名字")
    private String researchTitle;

    @ApiModelProperty(value = "研究类型id")
    private String typeId;

    @ApiModelProperty(value = "老师ID")
    private String teacherId;

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
