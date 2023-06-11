package com.insy2s.KeyCloakAuth.controller;


import com.insy2s.KeyCloakAuth.model.LoginRequest;
import com.insy2s.KeyCloakAuth.model.LoginResponse;
import com.insy2s.KeyCloakAuth.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/keycloak/auth")
public class LoginController {
@Autowired
private RestTemplate restTemplate;

	@Autowired
	LoginService loginservice;
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login (@RequestBody LoginRequest loginrequest) {

		return loginservice.login(loginrequest);
		


	}
		@PostMapping("/logout")
		public ResponseEntity<String> logout (HttpServletRequest httpServletRequest ,@RequestBody String tokenRefrech) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			String authorizationHeader = httpServletRequest.getHeader("Authorization");
			String token = null;
			if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
				token = authorizationHeader.substring(7); // Remove "Bearer " prefix
			}
			System.out.println("token "+token);
			System.out.println("token "+tokenRefrech);

			return loginservice.logout(token,tokenRefrech);


		}
			return ResponseEntity.status(500).body("Eroor ");
		}}



