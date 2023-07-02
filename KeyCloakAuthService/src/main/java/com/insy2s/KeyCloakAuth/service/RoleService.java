package com.insy2s.KeyCloakAuth.service;

import com.insy2s.KeyCloakAuth.model.LoginRequest;
import com.insy2s.KeyCloakAuth.model.LoginResponse;
import com.insy2s.KeyCloakAuth.model.Role;
import com.insy2s.KeyCloakAuth.model.TokenResponse;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

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

    @Value("${keycloak.realm}")
    private String realm;


    private final Keycloak keycloak;


    public RoleService(RoleRepository roleRepository, Keycloak keycloak) {
        this.roleRepository = roleRepository;
        this.keycloak = keycloak;

    }

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
            Set<String> processedRoleNames = new HashSet<>();

            if (roleRepresentations != null) {
                for (RoleRepresentation roleRepresentation : roleRepresentations) {
                    // Convert RoleRepresentation to Role
                    Role role = new Role();

                    role.setIdkeyCloak(roleRepresentation.getId());
                    role.setName(roleRepresentation.getName());


                    // Check if role with the same name has already been processed
                    if (!processedRoleNames.contains(role.getName())) {
                        Role existingRole = roleRepository.findByName(role.getName()).orElse(null);
                        if (existingRole == null) {
                            roleRepository.save(role);
                        }
                        processedRoleNames.add(role.getName());
                        roles.add(role);
                    }


                }
            }

            return roles;
        } else {
            // Handle the failure case if needed
            return Collections.emptyList();
        }
    }

public Role createRole(Role role)
{

//    LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
//    ResponseEntity<LoginResponse> token=loginService.login(loginRequest);
//
//    // Step 1: Create the role in Keycloak
//    RoleRepresentation roleRepresentation = new RoleRepresentation();
//    roleRepresentation.setName(role.getName());
//    role.setDescription("Role Description");
//
//
//    HttpHeaders headersuser = new HttpHeaders();
//    headersuser.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//    headersuser.setBearerAuth(token.getBody().getAccess_token());
//    HttpEntity<RoleRepresentation> request = new HttpEntity<>(roleRepresentation, headersuser);
//    String userUrl = issueUrlUser+"/roles/"; // replace {realm} with your realm name
//    URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
//
//    ParameterizedTypeReference<RoleRepresentation> responseType = new ParameterizedTypeReference<>() {};
//    ResponseEntity<RoleRepresentation> responseRole = restTemplate.exchange(uri, HttpMethod.POST, request, responseType);
//    Role savedRole = new Role();
//    if (responseRole.getStatusCode().is2xxSuccessful()) {
//
//
//        // Step 2: Save the role in the local database
//
//        savedRole.setName(role.getName());
//        savedRole.setDescription(role.getDescription());
//        roleRepository.save(savedRole);
//    }
//
//
//
//    return ResponseEntity.ok(savedRole) ;
        //////////////////////////

    Role savedRole = new Role();
    savedRole.setName(role.getName());
    savedRole.setDescription(role.getDescription());
    roleRepository.save(savedRole);

    return savedRole;
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