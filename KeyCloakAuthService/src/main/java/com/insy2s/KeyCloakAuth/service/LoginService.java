package com.insy2s.KeyCloakAuth.service;



import com.insy2s.KeyCloakAuth.model.LoginRequest;
import com.insy2s.KeyCloakAuth.model.LoginResponse;
import com.insy2s.KeyCloakAuth.model.Response;
import com.insy2s.KeyCloakAuth.model.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

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
	

	public ResponseEntity<LoginResponse> login(LoginRequest loginrequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", clientId);
		map.add("client_secret", clientSecret);
		map.add("grant_type", grantType);
		map.add("username", loginrequest.getUsername());
		map.add("password", loginrequest.getPassword());
		
		HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(map,headers);
		
		ResponseEntity<LoginResponse> response = restTemplate.postForEntity(issueUrl, httpEntity, LoginResponse.class);
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
