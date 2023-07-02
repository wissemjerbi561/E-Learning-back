package com.insy2s.KeyCloakAuth.controller;

import com.insy2s.KeyCloakAuth.model.Role;
import com.insy2s.KeyCloakAuth.service.RoleService;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keycloak/roles")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @GetMapping("/test")
    String test( )
    {
        return "hello";
    }
    @GetMapping("/getAll")
    List<Role> getRole( )
    {
        return roleService.getRoles();
    }

    @GetMapping("/getAllRoles")
    List<Role> getAllRoles( )
    {
        return roleService.getAllRoles();
    }


    @PostMapping("/create")
    Role create(@RequestBody  Role role)

    {

        return roleService.createRole(role);
    }

}
