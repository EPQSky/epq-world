package icu.epq.common.granter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登陆用户信息
 *
 * @author epqsky
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "用户登陆授权认证信息", description = "用户基本信息")
public class BaseGranter implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "登陆用户类型")
    private String userType;

    @ApiModelProperty(value = "用户名")
    private String name;

    @ApiModelProperty(value = "用户UID")
    private Integer userCode;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "token")
    private String token;

}
