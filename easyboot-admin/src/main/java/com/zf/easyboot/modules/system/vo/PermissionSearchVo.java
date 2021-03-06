package com.zf.easyboot.modules.system.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 疯信子
 * @version 1.0
 * @date 2019/10/15.
 */
@Data
public class PermissionSearchVo extends PageInfoVo {

    @ApiModelProperty(value = "模糊查询权限别名")
    private String aliasName;
}
