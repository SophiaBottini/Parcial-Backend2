package com.digitalmedia.users.service;

import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.model.dto.UserKeycloakList;
import com.digitalmedia.users.repository.IUserKeycloackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserKeycloakServiceImpl implements IUserKeycloakService{
    @Autowired
    private IUserKeycloackRepository userKeycloackRepository;

    @Override
    public List<UserKeycloakList> getUsersNotAdmin() {
        return userKeycloackRepository.getUsersNotAdmin();
    }

    @Override
    public UserKeycloak getUserById(String imdbId) {
        return userKeycloackRepository.getUserById(imdbId);
    }

    @Override
    public UserKeycloak getUserByUsername(String username) {
        return userKeycloackRepository.getUserByUsername(username);
    }
}
