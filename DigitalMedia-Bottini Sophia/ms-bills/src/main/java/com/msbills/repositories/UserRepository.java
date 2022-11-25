package com.msbills.repositories;

import com.msbills.models.Dto.User;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepository {
    private FeignUserRepository feignSubscriptionRepository;

    public UserRepository(FeignUserRepository feignSubscriptionRepository) {
        this.feignSubscriptionRepository = feignSubscriptionRepository;
    }

    public User findByUsername(String customer){
        return feignSubscriptionRepository.findUser(customer);

    }


}
