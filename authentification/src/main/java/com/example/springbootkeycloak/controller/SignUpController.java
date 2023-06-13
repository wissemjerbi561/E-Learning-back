//package com.example.springbootkeycloak.controller;
//
//import org.keycloak.admin.client.Keycloak;
//import org.keycloak.admin.client.resource.UsersResource;
//import org.keycloak.representations.account.UserRepresentation;
//import org.keycloak.representations.idm.CredentialRepresentation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.validation.Valid;
//import java.util.Collections;
//
//@RestController
//@Validated
//public class SignUpController {
//
//    @Autowired
//    private Keycloak keycloak;
//
//    @PostMapping("/signup")
//    public ResponseEntity<String> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
//
//        UsersResource usersResource = keycloak.realm("myrealm").users();
//
//        // create a new user in Keycloak
//        UserRepresentation user = new UserRepresentation();
//        user.setEnabled(true);
//        user.setUsername(signUpRequest.getEmail());
//        user.setFirstName(signUpRequest.getFirstName());
//        user.setLastName(signUpRequest.getLastName());
//        user.setEmail(signUpRequest.getEmail());
//        user.setAttributes(Collections.singletonMap("phone", signUpRequest.getPhone()));
//        Response response = usersResource.create(user);
//        String userId = response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
//
//        // set user password
//        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
//        credentialRepresentation.setType(CredentialRepresentation.PASSWORD);
//        credentialRepresentation.setValue(signUpRequest.getPassword());
//        credentialRepresentation.setTemporary(false);
//
//        UserResource userResource = usersResource.get(userId).toRepresentation();
//        userResource.setCredentials(Collections.singletonList(credentialRepresentation));
//        usersResource.get(userId).update(userResource);
//
//        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
//    }
//}
