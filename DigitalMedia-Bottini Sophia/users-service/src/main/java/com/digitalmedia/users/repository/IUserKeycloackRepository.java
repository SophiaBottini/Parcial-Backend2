package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.model.dto.UserKeycloakList;

import java.util.List;

public interface IUserKeycloackRepository {

    List<UserKeycloakList> getUsersNotAdmin();

    UserKeycloak getUserById(String imdbId);

    UserKeycloak getUserByUsername(String username);
}
