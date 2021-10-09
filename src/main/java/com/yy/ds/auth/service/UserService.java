package com.yy.ds.auth.service;

import javax.servlet.http.HttpServletRequest;

import com.yy.ds.auth.dto.SecurityUser;
import com.yy.ds.auth.feign.SystemFeignClient;
import com.yy.ds.common.api.AuthCode;
import com.yy.ds.common.constant.AuthConstant;
import com.yy.ds.common.dto.UserDto;
import com.yy.ds.common.exception.Asserts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cn.hutool.crypto.digest.BCrypt;

/*
 *@Description: 用户管理业务类
 *@ClassAuthor: tengYong
 *@Date: 2021-01-23 15:32:46
*/
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private SystemFeignClient systemFeignClient;

    // @Autowired
    // private UmsMemberService memberService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 访问Oauth2获取token接口时，会先到此，该方法走完会到JwtTokenEnhancer中的enhance
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String clientId = request.getParameter("client_id");
        String password = request.getParameter("password");
        UserDto userDto = null;
        if (AuthConstant.ADMIN_CLIENT_ID.equals(clientId)) {
            userDto = systemFeignClient.loadUserByUsername(username);
        }
        if (userDto == null || !BCrypt.checkpw(password, userDto.getPassword())) {
            Asserts.fail(AuthCode.USERNAME_PASSWORD_ERROR);
        }
        userDto.setClientId(clientId);
        SecurityUser securityUser = new SecurityUser(userDto);
        if (!securityUser.isEnabled()) {
            Asserts.fail(AuthCode.ACCOUNT_DISABLED);
        } else if (!securityUser.isAccountNonLocked()) {
            Asserts.fail(AuthCode.ACCOUNT_LOCKED);
        } else if (!securityUser.isAccountNonExpired()) {
            Asserts.fail(AuthCode.ACCOUNT_EXPIRED);
        } else if (!securityUser.isCredentialsNonExpired()) {
            Asserts.fail(AuthCode.CREDENTIALS_EXPIRED);
        }
        return securityUser;
    }

}
