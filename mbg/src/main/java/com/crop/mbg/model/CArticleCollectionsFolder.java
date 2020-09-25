package com.crop.mbg.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@ApiModel(value = "com.crop.mbg.model.CArticleCollectionsFolder",description="")
@Data
public class CArticleCollectionsFolder implements Serializable {
    @ApiModelProperty(value="",name="id")
    private Long id;

    @ApiModelProperty(value="用户id",name="userId")
    private Long userId;

    @ApiModelProperty(value="文件夹名称",name="name")
    @Length(max = 32, message = "文件夹名称名长度最长为32")
    private String name;

    @ApiModelProperty(value="是否为默认文件夹,0-是，1-否",name="isDefault")
    private Integer isDefault;

    @ApiModelProperty(value="是否私人文件夹，0-否，1-是，默认0",name="isPrivate")
    private Integer isPrivate;

    @ApiModelProperty(value="文件夹描述",name="description")
    @Length(max = 64, message = "文件夹描述名长度最长为64")
    private String description;

    @ApiModelProperty(value="",name="createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    @ApiModelProperty(value="",name="updateTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userId=").append(userId);
        sb.append(", name=").append(name);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", isPrivate=").append(isPrivate);
        sb.append(", description=").append(description);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}