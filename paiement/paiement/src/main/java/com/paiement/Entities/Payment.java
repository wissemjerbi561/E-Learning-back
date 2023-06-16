package com.paiement.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.util.Date;
@Entity
@Getter
@Setter
@Data@AllArgsConstructor
@NoArgsConstructor
@Table(name = "payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    // the product name
    private String name;
    private long amount;
    //local date time
    private long quantity;
    @CreationTimestamp
    private Date date;
    private String paymentStatus;

    //  currency like usd, eur ...
    private String currency;
    // our success and cancel url stripe will redirect to this links
    @Transient
    private String successUrl;
    @Transient
    private String cancelUrl;
    private String customerId;
    private String sessionId;
    @OneToOne
    private Cart cart;

    //@Transient private Member member;
}
