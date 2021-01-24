package com.yy.auth.service;

import com.yy.common.dto.UserDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 *@Description: 调用system服务
 *@ClassAuthor: tengYong
 *@Date: 2021-01-23 15:31:57
*/
@FeignClient("system")
public interface UmsAdminService {

    @GetMapping("/admin/loadByUsername")
    UserDto loadUserByUsername(@RequestParam String username);
}
