package com.example.springbootkeycloak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/apprenant")
    public ResponseEntity<String> getAnonymous() {
        return ResponseEntity.ok("Hello Apprenant");
    }

    @GetMapping("/tuteur-academique")
    public ResponseEntity<String> getAdmin(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("firstname");
        String userEmail = (String) token.getTokenAttributes().get("email");
        List<String> roles = (List<String>) token.getTokenAttributes().get("realm_access.roles");
        Map<String, Object> otherClaims = token.getTokenAttributes();

        return ResponseEntity.ok("hello tuteur academique"+otherClaims);
    }

    @GetMapping("/tuteur-professionnel")
    public ResponseEntity<String> getUser(Principal principal) {
        JwtAuthenticationToken token = (JwtAuthenticationToken) principal;
        String userName = (String) token.getTokenAttributes().get("firstName");
        String userEmail = (String) token.getTokenAttributes().get("email");
        Map<String, Object> otherClaims = token.getTokenAttributes();
        return ResponseEntity.ok("hello tuteur professionnel");
    }


}