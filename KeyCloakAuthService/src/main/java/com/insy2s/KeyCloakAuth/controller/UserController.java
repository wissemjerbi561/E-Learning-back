package com.insy2s.KeyCloakAuth.controller;

import com.insy2s.KeyCloakAuth.dto.UserDto;
import com.insy2s.KeyCloakAuth.model.User;
import com.insy2s.KeyCloakAuth.service.KeycloakService;
import com.insy2s.KeyCloakAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/keycloak/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    KeycloakService keycloakService;
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
    @GetMapping("/findById/{id}")
    ResponseEntity<User> findById(@PathVariable Long id )
    {
        return ResponseEntity.status(200).body(userService.getUserById(id ));
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

        return userService.createUser(user);
    }

    @PostMapping(value = "/addUser")
    ResponseEntity saveUser(@RequestBody UserDto user ){

        return userService.addUser(user);
    }
    @PostMapping(value = "/create")
    ResponseEntity addUser(@RequestBody UserDto user){

        return keycloakService.createUser(user);
    }
    @PutMapping(value = "/")
    ResponseEntity desActiveUser(@RequestParam String username){

        return userService.desActiveUser( username);
    }
    @DeleteMapping(value = "/{id}")
    ResponseEntity deleteUser(@PathVariable int id){

        return userService.deleteUser( id);
    }

}
