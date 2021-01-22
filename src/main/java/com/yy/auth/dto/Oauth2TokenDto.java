package com.yy.auth.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/*
 *@Description: Oauth2获取Token返回信息封装
 *@ClassAuthor: tengYong
 *@Date: 2021-01-22 13:18:38
*/
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class Oauth2TokenDto {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
