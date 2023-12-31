package com.insy2s.KeyCloakAuth.service;


import com.insy2s.KeyCloakAuth.ServiceMail.EmailServiceImp;
import com.insy2s.KeyCloakAuth.dto.UserDto;
import com.insy2s.KeyCloakAuth.model.*;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import com.insy2s.KeyCloakAuth.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserService {
    @Autowired
    private EmailServiceImp emailServiceImp;
    @Autowired
    Keycloak keycloak;

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
    @Value("${spring.security.oauth2.resourceserver.jwt.roles}")
    private String rolesKey;
    @Value("${keycloak.realm}")
    private String realm;
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

    public ResponseEntity addUser(UserDto user){




        LoginRequest loginRequest = new LoginRequest("insy2s", "insy2s");
        ResponseEntity<LoginResponse> token = loginService.login(loginRequest);

        HttpHeaders headersuser = new HttpHeaders();
        headersuser.setBearerAuth(token.getBody().getAccess_token());



/*        String randomPassword = generateRandomPassword();
        System.out.println("password" + randomPassword);
        user.setPassword(randomPassword);*/




        Collection<Role> targetRoles = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        Optional<Role> roleUser = Optional.of(new Role());

        for (Role role : roles) {
            String roleName = role.getName();
            roleUser = roleRepository.findByName(roleName);
            targetRoles.add(roleUser.get());
        }
        List<String> userRep ;
        List<String> userRoles = List.of("ADMIN","member");

        Map<String, List<String>> clientRoles = new HashMap<>();
        clientRoles.put(clientId, userRoles);
        userRep = targetRoles.stream().map(Role::getName).collect(Collectors.toList());
        // Retrieve the RealmResource

        RealmResource realmResource = keycloak.realm(realm);



        User userSaved = new User();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setCredentials(Collections.singletonList(getPasswordCredentials(user.getPassword())));
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(userRoles);
        userRepresentation.setEmailVerified(false);



        HttpEntity<UserRepresentation> requestRep = new HttpEntity<>(userRepresentation,headersuser);

        String userUrl = issueUrlUser + "/users/";
        System.out.println(userUrl + "issueUrl");
        URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();

        ResponseEntity<UserRepresentation> response = restTemplate.postForEntity(uri, requestRep, UserRepresentation.class);
        System.out.println("status code = " + response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {

            userSaved.setId(user.getId());
            userSaved.setFirstname(user.getFirstname());
            userSaved.setUsername(user.getUsername());
            userSaved.setLastname(user.getLastname());
            userSaved.setEmail(user.getEmail());
            userSaved.setPassword(user.getPassword());
            userSaved.setRoles(targetRoles);
            userRepository.save(userSaved);
        }

        return ResponseEntity.status(201).body(userSaved);
    }

    public ResponseEntity createUser(UserDto user) {

//            System.out.println("rolesKey" +rolesKey);
//
//            String randomPassword = generateRandomPassword();
//            System.out.println("password"+randomPassword);
//            user.setPassword(randomPassword);
//            LoginRequest loginRequest=new LoginRequest("insy2s","insy2s");
//            ResponseEntity <LoginResponse>token=loginService.login(loginRequest);
//
//            HttpHeaders headersuser = new HttpHeaders();
//            headersuser.setBearerAuth(token.getBody().getAccess_token());
//
//        Optional<Role> role=roleRepository.findByName(user.getRole().toString());
//            Collection<Role> roles = new ArrayList<>();
//            List<String> userRep= new ArrayList<>();
//        if(role != null && role.isPresent() ){
//
//            roles.add(role.get());
//
//            userRep= Collections.singletonList(String.valueOf(roles));
//        }
//
//            User userSaved = new User();
//
//
//
//
//            UserRepresentation userRepresentation = new UserRepresentation();
//            userRepresentation.setFirstName(user.getFirstname());
//            userRepresentation.setLastName(user.getLastname());
//            userRepresentation.setEmail(user.getEmail());
//            userRepresentation.setUsername(user.getUsername());
//            userRepresentation.setCredentials(Collections.singletonList(getPasswordCredentials(user.getPassword())));
//            userRepresentation.setEnabled(true);
//            //userRepresentation.setRealmRoles(userRep);
//            userRepresentation.setEmailVerified(false);
//            HttpEntity<UserRepresentation> request = new HttpEntity<>(userRepresentation, headersuser);
//
//            String userUrl = issueUrlUser+"/users/";
//            System.out.println(userUrl+"issueUrl");
//            URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
//            String userSearchedFromKeycloak=getUserByIdFromKeycloak(user.getUsername());
//            System.out.println(userSearchedFromKeycloak +"userSearched");
//         //   if( userSearchedFromKeycloak == null)    {
//
//                    ResponseEntity<UserRepresentation> response =
//                            restTemplate.postForEntity(uri, request, UserRepresentation.class
//                            );
//            System.out.println("statut code  = "+response.getStatusCode().is2xxSuccessful());
//                    if(response.getStatusCode().is2xxSuccessful())
//                    {
//
//                       String userSearchedFromKeycloak1=getUserByIdFromKeycloak(user.getUsername());
//
//
//                        userSaved.setId(user.getId());
//                        userSaved.setFirstname(user.getFirstname());
//                        userSaved.setUsername(user.getUsername());
//                        userSaved.setLastname(user.getLastname());
//                        userSaved.setEmail(user.getEmail());
//                        userSaved.setPassword(user.getPassword());
//                        userSaved.setRoles(roles);
//                        userRepository.save(userSaved);
//
//                    }
//                     return  ResponseEntity.status(201).body(userSaved);}


        System.out.println("rolesKey" + rolesKey);

        String randomPassword = generateRandomPassword();
        System.out.println("password" + randomPassword);
        user.setPassword(randomPassword);

        LoginRequest loginRequest = new LoginRequest("insy2s", "insy2s");
        ResponseEntity<LoginResponse> token = loginService.login(loginRequest);

        HttpHeaders headersuser = new HttpHeaders();
        headersuser.setBearerAuth(token.getBody().getAccess_token());

        Collection<Role> targetRoles = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        Optional<Role> roleUser = Optional.of(new Role());

        for (Role role : roles) {
            String roleName = role.getName();
            roleUser = roleRepository.findByName(roleName);
            targetRoles.add(roleUser.get());
        }
        List<String> userRep ;

        userRep = targetRoles.stream().map(Role::getName).collect(Collectors.toList());
        // Retrieve the RealmResource

        RealmResource realmResource = keycloak.realm(realm);

        // Get the UsersResource
        UsersResource usersResource = realmResource.users();

        User userSaved = new User();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setCredentials(Collections.singletonList(getPasswordCredentials(user.getPassword())));
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(userRep);
        userRepresentation.setEmailVerified(false);
        //userRepresentation.



        HttpEntity<UserRepresentation> request = new HttpEntity<>(userRepresentation, headersuser);

        String userUrl = issueUrlUser + "/users/";
        System.out.println(userUrl + "issueUrl");
        URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
        String userSearchedFromKeycloak = getUserByIdFromKeycloak(user.getUsername());
        System.out.println(userSearchedFromKeycloak + "userSearched");

        ResponseEntity<UserRepresentation> response = restTemplate.postForEntity(uri, request, UserRepresentation.class);
        System.out.println("status code = " + response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {


            userSaved.setId(user.getId());
            userSaved.setFirstname(user.getFirstname());
            userSaved.setUsername(user.getUsername());
            userSaved.setLastname(user.getLastname());
            userSaved.setEmail(user.getEmail());
            userSaved.setPassword(user.getPassword());
            userSaved.setRoles(targetRoles);
            emailServiceImp.sendmail(user.getUsername(),user.getEmail(),user.getPassword());

            System.out.println("---------------------------------------------");
            userRepository.save(userSaved);
        }

        return ResponseEntity.status(201).body(userSaved);
    }

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
    public User getUserById(Long id)
    {
        return userRepository.findUserById(id);
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
    public ResponseEntity ajoutUser(UserDto user) {
        System.out.println("rolesKey" + rolesKey);

        LoginRequest loginRequest = new LoginRequest("insy2s", "insy2s");
        ResponseEntity<LoginResponse> token = loginService.login(loginRequest);

        HttpHeaders headersuser = new HttpHeaders();
        headersuser.setBearerAuth(token.getBody().getAccess_token());

        Collection<Role> targetRoles = new ArrayList<>();
        Collection<Role> roles = user.getRoles();
        Optional<Role> roleUser = Optional.of(new Role());

        for (Role role : roles) {
            String roleName = role.getName();
            roleUser = roleRepository.findByName(roleName);
            targetRoles.add(roleUser.get());
        }
        List<String> userRep ;

        userRep = targetRoles.stream().map(Role::getName).collect(Collectors.toList());
        // Retrieve the RealmResource

        RealmResource realmResource = keycloak.realm(realm);

        // Get the UsersResource
        UsersResource usersResource = realmResource.users();

        User userSaved = new User();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(user.getFirstname());
        userRepresentation.setLastName(user.getLastname());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setCredentials(Collections.singletonList(getPasswordCredentials(user.getPassword())));
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(userRep);
        userRepresentation.setEmailVerified(false);

        HttpEntity<UserRepresentation> request = new HttpEntity<>(userRepresentation, headersuser);

        String userUrl = issueUrlUser + "/users/";
        System.out.println(userUrl + "issueUrl");
        URI uri = UriComponentsBuilder.fromUriString(userUrl).buildAndExpand("KeyClock-INSY2S-E-LEARING").toUri();
        String userSearchedFromKeycloak = getUserByIdFromKeycloak(user.getUsername());
        System.out.println(userSearchedFromKeycloak + "userSearched");

        ResponseEntity<UserRepresentation> response = restTemplate.postForEntity(uri, request, UserRepresentation.class);
        System.out.println("status code = " + response.getStatusCode().is2xxSuccessful());
        if (response.getStatusCode().is2xxSuccessful()) {

            userSaved.setId(user.getId());
            userSaved.setFirstname(user.getFirstname());
            userSaved.setUsername(user.getUsername());
            userSaved.setLastname(user.getLastname());
            userSaved.setEmail(user.getEmail());
            userSaved.setPassword(user.getPassword());
            userSaved.setRoles(targetRoles);

            userRepository.save(userSaved);
        }
        return ResponseEntity.status(201).body(userSaved);
    }


}
