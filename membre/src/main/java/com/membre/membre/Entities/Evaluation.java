package com.membre.membre.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluation")
public class Evaluation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;
    private float finalNote;
    @ManyToMany
    @JoinTable(name="member_evaluation", joinColumns = @JoinColumn(name = "evaluationId"),
            inverseJoinColumns = @JoinColumn(name = "memberId"))
    private List<Member> members;
}
