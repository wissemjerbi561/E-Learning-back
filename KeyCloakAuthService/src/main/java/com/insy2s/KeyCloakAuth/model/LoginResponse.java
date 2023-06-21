package com.insy2s.KeyCloakAuth.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;

@Data
public class LoginResponse {
	
	private String access_token ;
	private Long userId ;
	private String username ;
	Collection<Role> roles = new ArrayList<>();
	private String refresh_token ;
	private String expires_in;
	private String refresh_expires_in;
	private String token_type;

	
	
	
	

}
