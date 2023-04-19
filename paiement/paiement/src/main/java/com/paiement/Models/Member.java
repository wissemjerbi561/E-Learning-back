package com.paiement.Models;

import lombok.Data;

@Data
public class Member {
    private Long memberId;
    private String name;
    private String email;
    private String password;
}