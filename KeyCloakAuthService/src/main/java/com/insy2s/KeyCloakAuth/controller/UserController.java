package com.insy2s.KeyCloakAuth.controller;

import com.insy2s.KeyCloakAuth.dto.UserDto;
import com.insy2s.KeyCloakAuth.model.User;
import com.insy2s.KeyCloakAuth.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.Consumes;
import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/api/keycloak/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/test")
    String test( )
    {
        return "hello";
    }
    @GetMapping("/find")
    ResponseEntity<User> getUser(@RequestParam String username )
    {
        return ResponseEntity.status(200).body(userService.getUser(username ));
    }
    @GetMapping("/")
    ResponseEntity getAllUsers( )
    {
        return userService.listUsers( );
    }
//    @GetMapping("/tesst")
//    List<UserRepresentation> test( )
//    {
//        return userService.test( );
//    }
    @PostMapping(value = "/signup")
    ResponseEntity createUser(@RequestBody UserDto user){

        return userService.createUser( user);
    }
    @PutMapping(value = "/")
    ResponseEntity desActiveUser(@RequestParam String username){

        return userService.desActiveUser( username);
    }
    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteUser(@PathVariable String id){

        return userService.deleteUser( id);
    }

}
