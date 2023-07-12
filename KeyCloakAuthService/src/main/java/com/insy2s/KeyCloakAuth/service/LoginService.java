package com.insy2s.KeyCloakAuth.service;



import com.insy2s.KeyCloakAuth.model.*;
import com.insy2s.KeyCloakAuth.repository.RoleRepository;
import com.insy2s.KeyCloakAuth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService {
	
	@Autowired
	RestTemplate restTemplate;

	
	@Value("${spring.security.oauth2.client.provider.keycloak.token-uri}")
	private String issueUrl;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-id}")
	private String clientId;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.client-secret}")
	private String clientSecret;
	
	@Value("${spring.security.oauth2.client.registration.oauth2-client-credentials.authorization-grant-type}")
	private String grantType;

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	public ResponseEntity<LoginResponse> login(LoginRequest loginrequest) {




		String username = loginrequest.getUsername();
		String password = loginrequest.getPassword();

		User user = new User();
		User usertosave = new User();
		user = userRepository.findByUsername(username);


		if(user ==null){
			usertosave.setUsername(username);
			usertosave.setPassword(password);
			usertosave.setRoles(roleRepository.findAll());
		user=userRepository.save(usertosave);

		}

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("grant_type", grantType);
		map.add("username", loginrequest.getUsername());
		map.add("password", loginrequest.getPassword());
		map.add("user",username);
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map,headers);
		
		ResponseEntity<LoginResponse> response = restTemplate.postForEntity(issueUrl, httpEntity, LoginResponse.class);
		LoginResponse loginResponse = response.getBody();
		loginResponse.setUserId(user.getId());
		loginResponse.setUsername(username);
		loginResponse.setRoles(user.getRoles());
		TokenResponse token=new TokenResponse();
		token.setToken(response.getBody().getAccess_token());





		return  ResponseEntity.status(200).body(response.getBody());
	
		
	}
	public ResponseEntity<String> logout(String jwt, String tokenRefresh) {
		HttpHeaders headers = new HttpHeaders();

		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type", grantType);
		map.add("client_id", clientId);
		map.add("refresh_token", tokenRefresh);
		map.add("client_secret", clientSecret);

		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.setBearerAuth(jwt);

		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map, headers);

		ResponseEntity<Response> responseEntity = restTemplate.exchange(
				"https://keycloak.fethi.synology.me/auth/realms/KeyClock-INSY2S/protocol/openid-connect/logout",
				HttpMethod.POST,
				httpEntity,
				Response.class
		);

		if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT) {
			return ResponseEntity.ok("Logged out successfully");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}







}
