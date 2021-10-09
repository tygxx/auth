package com.yy.ds.auth.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "authconfig")
public class AuthConfigProperties {

    /** 制作jks证书时，指定的密码 */
    private String secret;

    /** token有效时长 */
    private Integer accessTokenValiditySeconds;

    /** 多长时间刷新token */
    private Integer refreshTokenValiditySeconds;
}