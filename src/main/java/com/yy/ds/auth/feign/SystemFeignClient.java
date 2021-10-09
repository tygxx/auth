package com.yy.ds.auth.feign;

import com.yy.ds.common.dto.UserDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "system")
public interface SystemFeignClient {

    @GetMapping("/user/username/{username}")
    UserDto loadUserByUsername(@PathVariable String username);

}
