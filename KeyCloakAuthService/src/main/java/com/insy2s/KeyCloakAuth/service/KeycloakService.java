package com.insy2s.KeyCloakAuth.service;

import com.insy2s.KeyCloakAuth.dto.UserDto;
import com.insy2s.KeyCloakAuth.model.Role;
import com.insy2s.KeyCloakAuth.model.User;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import com.insy2s.KeyCloakAuth.repository.UserRepository;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.BeanParam;
import javax.ws.rs.core.Response;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class KeycloakService {


    private final Keycloak keycloak;
    private final RestTemplate restTemplate;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public KeycloakService(Keycloak keycloak, RestTemplate restTemplate, RoleRepository roleRepository, UserRepository userRepository) {
        this.keycloak = keycloak;
        this.restTemplate = restTemplate;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public ResponseEntity<?> createUser(UserDto user) {
        Role role = roleRepository.findByName(user.getRole()).orElse(null);

        if (role == null) {
            return ResponseEntity.badRequest().body("Role not found");
        }

        // Generate a random password
        String randomPassword = generateRandomPassword();

        // Create the Keycloak user
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(true);

        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(randomPassword);
        credential.setTemporary(false);

        userRepresentation.setCredentials(Collections.singletonList(credential));

        RealmResource realmResource = keycloak.realm(keycloak.realm("KeyClock-INSY2S-E-LEARING").toString());
        UsersResource usersResource = realmResource.users();
        Response response = usersResource.create(userRepresentation);
        if (response.getStatus() != 201) {
            return ResponseEntity.status(response.getStatus()).body("Failed to create user in Keycloak");
        }

        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");

        // Assign the role to the user
        RolesResource rolesResource = realmResource.roles();
        RoleRepresentation roleRepresentation = rolesResource.get(role.getName()).toRepresentation();
        usersResource.get(userId).roles().realmLevel().add(Arrays.asList(roleRepresentation));

        // Save the user in your application's database
        User userSaved = new User();
        userSaved.setId(Long.valueOf(userId));
        userSaved.setFirstname(user.getFirstname());
        userSaved.setUsername(user.getUsername());
        userSaved.setLastname(user.getLastname());
        userSaved.setEmail(user.getEmail());
        userSaved.setPassword(randomPassword);
        userSaved.setRoles(Collections.singletonList(role));

        userRepository.save(userSaved);

        return ResponseEntity.status(201).body(userSaved);
    }

    private String generateRandomPassword() {
        // Your password generation logic here
        return "generated-password";
    }


}