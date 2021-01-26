package com.yy.auth.config;

import com.yy.common.config.BaseSwaggerConfig;
import com.yy.common.dto.SwaggerProperties;

import org.springframework.context.annotation.Configuration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 *@Description: Swagger API文档相关配置
 *@ClassAuthor: tengYong
 *@Date: 2021-01-23 15:09:06
*/
@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {

    @Override
    public SwaggerProperties swaggerProperties() {
        return SwaggerProperties.builder().apiBasePackage("com.yy.auth.controller").title("认证中心")
                .description("认证中心相关接口文档").contactName("ty").version("1.0").enableSecurity(true).build();
    }
}
