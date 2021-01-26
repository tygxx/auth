package com.yy.auth.controller;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/*
 *@Description: 获取RSA公钥接口
 *@ClassAuthor: tengYong
 *@Date: 2021-01-23 18:39:22
*/
@Api(tags = "KeyPairController", description = "公钥管理")
@RestController
public class KeyPairController {

    @Autowired
    private KeyPair keyPair;

    @ApiOperation("获取RSA公钥")
    @GetMapping("/rsa/publicKey")
    public Map<String, Object> getKey() {
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }

}
