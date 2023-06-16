package com.paiement.Entities;

import com.paiement.Entities.Payment;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
public class Member {
    private Long memberId;
    private String name;
    private String email;
    private String password;
    @OneToMany
    private List<Payment> payments;
}