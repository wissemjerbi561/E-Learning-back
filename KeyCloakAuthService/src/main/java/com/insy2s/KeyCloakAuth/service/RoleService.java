package com.insy2s.KeyCloakAuth.service;

import com.insy2s.KeyCloakAuth.model.LoginRequest;
import com.insy2s.KeyCloakAuth.model.LoginResponse;
import com.insy2s.KeyCloakAuth.model.Role;
import com.insy2s.KeyCloakAuth.model.TokenResponse;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class RoleService {
@Autowired
private RoleRepository roleRepository;
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private  LoginService loginService;
    @Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
    private String issueUrl;

    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
    private String clientId;
    @Value("${uri-user}")
    private String issueUrlUser;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
    private String clientSecret;
    @Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
    private String grantType;

public List<Role>getRoles(){
    return roleRepository.findAll();

}


    public List<Role> getAllRoles() {
        LoginRequest loginRequest = new LoginRequest("insy2s", "insy2s");
        ResponseEntity<LoginResponse> token = loginService.login(loginRequest);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token.getBody().getAccess_token());
        HttpEntity<?> httpEntity = new HttpEntity<>(headers);

        String url = "https://keycloak.fethi.synology.me/auth/admin/realms/KeyClock-INSY2S-E-LEARING/roles";
        URI uri = UriComponentsBuilder.fromUriString(url).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
        ParameterizedTypeReference<List<RoleRepresentation>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<RoleRepresentation>> responseRole = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, responseType);

        if (responseRole.getStatusCode().is2xxSuccessful()) {
            List<RoleRepresentation> roleRepresentations = responseRole.getBody();
            List<Role> roles = new ArrayList<>();

            if (roleRepresentations != null) {
                for (RoleRepresentation roleRepresentation : roleRepresentations) {
                    // Convert RoleRepresentation to Role
                    Role role = new Role();
                   // role.setId(Long.valueOf(roleRepresentation.getId()));
                    role.setName(roleRepresentation.getName());
                    roleRepository.save(role);
                    // Set other properties as needed
                    roles.add(role);
                }
            }

            return roles;
        } else {
            // Handle the failure case if needed
            return Collections.emptyList();
        }
    }

public ResponseEntity createUser(Role role)
{
    return ResponseEntity.ok(roleRepository.save(role)) ;
}
    public List<RoleRepresentation> listRoles() {
        LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
        ResponseEntity<LoginResponse> token=loginService.login(loginRequest);

        HttpHeaders headersuser = new HttpHeaders();
        headersuser.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headersuser.setBearerAuth(token.getBody().getAccess_token());
        HttpEntity<?> httpEntityuser = new HttpEntity<>(headersuser);
        String userUrl = issueUrlUser+"/roles/"; // replace {realm} with your realm name
        URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();

        ParameterizedTypeReference<List<RoleRepresentation>> responseType = new ParameterizedTypeReference<>() {};
        ResponseEntity<List<RoleRepresentation>> responseRole = restTemplate.exchange(uri, HttpMethod.GET, httpEntityuser, responseType);
        List<RoleRepresentation> roleList = responseRole.getBody();
        return roleList;
    }}