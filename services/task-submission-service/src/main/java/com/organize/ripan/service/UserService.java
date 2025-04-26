package com.organize.ripan.service;

import com.organize.ripan.model.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

// Give the name of the service, and the url on which the service has running
@FeignClient(name = "USER-SERVICE", url = "http://localhost:8001/")
public interface UserService {

    @GetMapping("/api/users/profile")
    public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
