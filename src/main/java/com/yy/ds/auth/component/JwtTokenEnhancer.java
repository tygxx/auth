package com.yy.ds.auth.component;

import java.util.HashMap;
import java.util.Map;

import com.yy.ds.auth.dto.SecurityUser;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

/*
 *@Description: JWT内容增强器
 *@ClassAuthor: tengYong
 *@Date: 2021-01-22 14:07:23
*/
@Component
public class JwtTokenEnhancer implements TokenEnhancer {

    /**
     * UserServiceImpl中loadUserByUsername方法走完到此，该方法走完后AuthController中的oAuth2AccessToken对象就有值了
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        // 把用户ID设置到JWT中
        info.put("id", securityUser.getId());
        info.put("client_id", securityUser.getClientId());
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
        return accessToken;
    }
}
