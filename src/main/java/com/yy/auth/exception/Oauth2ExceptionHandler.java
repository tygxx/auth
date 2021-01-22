package com.yy.auth.exception;

import com.yy.common.api.CommonResult;

import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 *@Description: 全局处理Oauth2抛出的异常 
 *@ClassAuthor: tengYong
 *@Date: 2021-01-22 13:28:47
*/
@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public CommonResult handleOauth2(OAuth2Exception e) {
        return CommonResult.failed(e.getMessage());
    }
}
