package com.example.springbootkeycloak.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/authentification/auth")
public class AuthController {

    private final String CLIENT_ID = "springboot-keycloak";
    private final String CLIENT_SECRET = "ec20e4e3-1695-4435-ad30-a8bf11b12a21";
    private final String GRANT_TYPE = "password";
    private final String REALM = "walid";
    private final String AUTH_SERVER = "http://localhost:8080/auth/realms/";

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestParam("username") String username, @RequestParam("password") String password) {

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("client_id", "springboot-keycloak");
        requestBody.add("client_secret", "ec20e4e3-1695-4435-ad30-a8bf11b12a21");
        requestBody.add("grant_type", GRANT_TYPE);
        requestBody.add("username", username);
        requestBody.add("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        String url = "http://localhost:8080/auth/realms/walid/protocol/openid-connect/token";
        ResponseEntity<TokenResponse> responseEntity = restTemplate.postForEntity(url, requestEntity, TokenResponse.class);

        HttpStatus statusCode = (HttpStatus) responseEntity.getStatusCode();
        if (statusCode == HttpStatus.OK) {
            TokenResponse tokenResponse = responseEntity.getBody();
            return ResponseEntity.ok(tokenResponse);
        } else {
            return ResponseEntity.status(statusCode).build();
        }

    }

    public Keycloak getAdminKeycloakUser() {
        return KeycloakBuilder.builder().serverUrl(AUTH_SERVER)
                .grantType("password").realm("walid")
                .clientId(CLIENT_ID)
                .username("admin")
                .password("admin")
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    }

    public RealmResource getRealm() {
        return getAdminKeycloakUser().realm("walid");
    }

    @PostMapping("/signup")
    public String createUser() {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername("username");
        userRepresentation.setFirstName("first-name");
        userRepresentation.setLastName("last-name");
        userRepresentation.setEmail("test@email.com");

        javax.ws.rs.core.Response response = KeycloakBuilder.builder().build().realm("walid").users().create(userRepresentation);
        if (response != null && response.getStatusInfo().getFamily() == Response.Status.Family.SUCCESSFUL) {
            return org.keycloak.admin.client.CreatedResponseUtil.getCreatedId(response);        // returns String (Id of created User)
        }
        return null;
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/http://localhost:8081/auth/signin";
    }

    }




