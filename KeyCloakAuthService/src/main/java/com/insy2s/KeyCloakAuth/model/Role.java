package com.insy2s.KeyCloakAuth.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String idkeyCloak;

    @Column
    private String name;
    @Column(length = 20)
    private String description;


    public Role(String name) {
        this.name = name;
    }



}