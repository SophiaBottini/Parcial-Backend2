package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.UserKeycloak;
import com.digitalmedia.users.model.dto.UserKeycloakList;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class UserKeycloackRepository implements IUserKeycloackRepository {

    @Autowired
    private Keycloak keycloakClient;

    @Value("${dh.keycloak.realm}")
    private String realm;

    @Override
    public List<UserKeycloakList> getUsersNotAdmin(){
        List<UserRepresentation> userFromKeaycloak = keycloakClient.realm(realm).users().list();
        List<UserRepresentation> usersEnabled = userFromKeaycloak.stream().filter(userRepresentation ->{
            List<GroupRepresentation> groups = keycloakClient.realm(realm).users().get(userRepresentation.getId()).groups();
            System.out.println("Grupos del Usuario: "+ userRepresentation.getUsername());
            System.out.println(groups);
            return groups.stream().noneMatch(s -> Objects.equals(s.getName(),"admin")); 
        }).collect(Collectors.toList());
        return usersEnabled.stream()
            .map(this::toUserKeycloakList)
            .collect(Collectors.toList());


    }

    @Override
    public UserKeycloak getUserById(String imdbId) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm).users()
                .get(imdbId).toRepresentation();
        return toUserKeycloak(userRepresentation);
    }

    @Override
    public UserKeycloak getUserByUsername(String username) {
        UserRepresentation userRepresentation = keycloakClient.realm(realm).users()
                .search(username).get(0);
        return toUserKeycloak(userRepresentation);
    }

    private UserKeycloakList toUserKeycloakList(UserRepresentation userRepresentation){
        return new UserKeycloakList(userRepresentation.getUsername(), userRepresentation.getEmail());
    }

    private UserKeycloak toUserKeycloak(UserRepresentation userRepresentation){
        return new UserKeycloak(userRepresentation.getUsername(), userRepresentation.getFirstName(),userRepresentation.getLastName(),userRepresentation.getEmail());
    }
}
