package com.digitalmedia.users.service;

import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.model.dto.UserKeycloakList;

import java.util.List;

public interface IUserKeycloakService {
    List<UserKeycloakList> getUsersNotAdmin();
    UserKeycloak getUserById(String imdbId);
    UserKeycloak getUserByUsername(String username);
}
