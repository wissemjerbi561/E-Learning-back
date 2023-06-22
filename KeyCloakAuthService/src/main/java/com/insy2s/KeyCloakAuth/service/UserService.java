package com.insy2s.KeyCloakAuth.service;


import com.insy2s.KeyCloakAuth.dto.UserDto;
import com.insy2s.KeyCloakAuth.model.*;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import com.insy2s.KeyCloakAuth.repository.UserRepository;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.idm.CredentialRepresentation;
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
import java.security.SecureRandom;
import java.util.*;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

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
//public List<UserRepresentation> test(){
//
//    Keycloak keycloak = KeycloakBuilder.builder()
//            .serverUrl(issueUrl+"/users/")
//            .realm("KeyClock-INSY2S")
//            .clientId(clientId)
//            .username("insy2s")
//            .grantType()
//            .password("insy2s")
//            .build();
//
//// Get the realm resource
//    RealmResource realmResource = keycloak.realm("KeyClock-INSY2S");
//
//// Get the users resource
//    UsersResource usersResource = realmResource.users();
//
//// Retrieve the list of users
//    List<UserRepresentation> userList = usersResource.list();
//return userList;
//}
public static String generateRandomPassword() {
    String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    StringBuilder sb = new StringBuilder();
    SecureRandom random = new SecureRandom();

    int length = 30; // Longueur du mot de passe souhaitée

    for (int i = 0; i < length; i++) {
        int randomIndex = random.nextInt(characters.length());
        char randomChar = characters.charAt(randomIndex);
        sb.append(randomChar);
    }

    return sb.toString();
    }
        public ResponseEntity createUser(UserDto user) {
            String randomPassword = generateRandomPassword();
            System.out.println("password"+randomPassword);
            user.setPassword(randomPassword);
            LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
            ResponseEntity <LoginResponse>token=loginService.login(loginRequest);

            HttpHeaders headersuser = new HttpHeaders();
            headersuser.setBearerAuth(token.getBody().getAccess_token());

        Optional<Role> role=roleRepository.findByName(user.getRole());
            Collection<Role> roles = new ArrayList<>();
        if(role != null && role.isPresent() ){

            roles.add(role.get());
        }

            User userSaved = new User();




            UserRepresentation userRepresentation = new UserRepresentation();
            userRepresentation.setFirstName(user.getFirstname());
            userRepresentation.setLastName(user.getLastname());
            userRepresentation.setEmail(user.getEmail());
            userRepresentation.setUsername(user.getUsername());
            userRepresentation.setCredentials(Collections.singletonList(getPasswordCredentials(user.getPassword())));
            userRepresentation.setEnabled(true);
            userRepresentation.setEmailVerified(false);
            HttpEntity<UserRepresentation> request = new HttpEntity<>(userRepresentation, headersuser);

            String userUrl = issueUrlUser+"/users/";
            System.out.println(userUrl+"issueUrl");
            URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
            String userSearchedFromKeycloak=getUserByIdFromKeycloak(user.getUsername());
            System.out.println(userSearchedFromKeycloak +"userSearched");
         //   if( userSearchedFromKeycloak == null)    {

                    ResponseEntity<UserRepresentation> response =
                            restTemplate.postForEntity(uri, request, UserRepresentation.class
                            );
            System.out.println("statut code  = "+response.getStatusCode().is2xxSuccessful());
                    if(response.getStatusCode().is2xxSuccessful())
                    {

//                        String userSearchedFromKeycloak1=getUserByIdFromKeycloak(user.getUsername());
//                        System.out.println("idSearched"+userSearchedFromKeycloak1);
//                        Long idSearched = Long.parseLong(userSearchedFromKeycloak1);
//                        user.setId((idSearched));
//                        userSaved.setId(user.getId());
                        userSaved.setFirstname(user.getFirstname());
                        userSaved.setUsername(user.getUsername());
                        userSaved.setLastname(user.getLastname());
                        userSaved.setEmail(user.getEmail());
                        userSaved.setPassword(user.getPassword());
                        userSaved.setRoles(roles);
                        userRepository.save(userSaved);

                    }
                     return  ResponseEntity.status(201).body(userSaved);}

           /*    else{


               return  ResponseEntity.status(302).body(" user found");}

                    }

*/
    private CredentialRepresentation getPasswordCredentials(String password) {
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);
        return credential;
    }
    public String getUserByIdFromKeycloak(String username) {
        String id=null;
        LoginRequest loginRequest = new LoginRequest("insy2s", "insy2s");
        ResponseEntity<LoginResponse> tokenResponse = loginService.login(loginRequest);
        String accessToken = tokenResponse.getBody().getAccess_token();

        // Set the Authorization header with the access token
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        // Build the user URL with the provided username
        String userApiUrl = issueUrlUser + "/users/";
        URI uri = UriComponentsBuilder.fromUriString(userApiUrl)
                .queryParam("username", username)
                .build()
                .toUri();

        // Send the GET request to Keycloak and retrieve the user representation
        ResponseEntity<UserRepresentation[]> response = restTemplate.exchange(uri, HttpMethod.GET, requestEntity, UserRepresentation[].class);
        if (response.getStatusCode().is2xxSuccessful()) {
            UserRepresentation[] userRepresentations = response.getBody();
            if (userRepresentations != null && userRepresentations.length > 0) {
                UserRepresentation userRepresentation = userRepresentations[0];
                id=userRepresentation.getId();


                // Map other necessary fields from userRepresentation to userSearchedFromKeycloak
            }
            return id;

        }
        else return id;

    }




public User getUser(String username)
{
    return userRepository.findByUsername(username);
}

    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    public ResponseEntity listUsers() {
        return  ResponseEntity.status(200).body(userRepository.findAll());
//        LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
//        ResponseEntity <TokenResponse>token=loginService.login(loginRequest);
//
//        HttpHeaders headersuser = new HttpHeaders();
//        headersuser.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//        headersuser.setBearerAuth(token.getBody().getToken());
//        HttpEntity<?> httpEntityuser = new HttpEntity<>(headersuser);
//        String userUrl = issueUrlUser+"/users/"; // replace {realm} with your realm name
//        URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S").toUri();
//
//        ParameterizedTypeReference<List<UserRepresentation>> responseType = new ParameterizedTypeReference<>() {};
//        ResponseEntity<List<UserRepresentation>> responseUser = restTemplate.exchange(uri, HttpMethod.GET, httpEntityuser, responseType);
//        List<UserRepresentation> userList = responseUser.getBody();
//        return ResponseEntity.ok(userList);
    }

    public ResponseEntity desActiveUser(String username) {
              LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
     ResponseEntity<LoginResponse> token=loginService.login(loginRequest);

       HttpHeaders headersuser = new HttpHeaders();
       headersuser.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headersuser.setBearerAuth(token.getBody().getAccess_token());
        HttpEntity<?> httpEntityuser = new HttpEntity<>(headersuser);String userUrl = issueUrlUser+"/users/"; // replace {realm} with your realm name
       URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S").toUri();

        ParameterizedTypeReference<List<UserRepresentation>> responseType = new ParameterizedTypeReference<>() {};
       ResponseEntity<List<UserRepresentation>> responseUser = restTemplate.exchange(uri, HttpMethod.PUT, httpEntityuser, responseType);
       List<UserRepresentation> userList = responseUser.getBody();
       return ResponseEntity.ok(userList);
    }
    public ResponseEntity deleteUser(int id){
    //remove from keycloak
    User userSearched=userRepository.findById((long) id).get();
    userRepository.delete(userSearched);
    return ResponseEntity.status(200).body("the User "+userSearched.getUsername()+" deleted with success");
    }


    }
