package com.paiement.Entities;

import com.paiement.Models.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
@Entity
@Data@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private int amount;
    @CreationTimestamp
    private Date date;
    private String paymentStatus;

    @OneToOne
    private Cart cart;

    @Transient private Member member;


}
