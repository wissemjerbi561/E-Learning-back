package com.membre.membre.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private float finalNote;
    private String gitLink;
    private String driveLink;
    @ManyToMany
    @JoinTable(name="member_evaluation", joinColumns = @JoinColumn(name = "memberId"),
            inverseJoinColumns = @JoinColumn(name = "evaluationId"))
    private List<Evaluation> evaluations;
    @Transient
    private List<Long> lstPositionId;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Position> positions=new ArrayList<>();







}