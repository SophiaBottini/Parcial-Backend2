package com.msbills.repositories;

import com.msbills.Configuration.OAuthFeignConfig;
import com.msbills.models.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name= "users-service", configuration = OAuthFeignConfig.class)
public interface FeignUserRepository {

    @GetMapping("/users/username/{username}")
    User findUser(@PathVariable("username") String username);

}
