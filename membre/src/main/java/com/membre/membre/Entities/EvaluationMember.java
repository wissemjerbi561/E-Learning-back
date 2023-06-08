package com.membre.membre.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "evaluationMember")
public class EvaluationMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long evaluationId;

    //private float finalNote;

/*    @ManyToMany
    @JoinTable(name="member_evaluation", joinColumns = @JoinColumn(name = "evaluationId"),
            inverseJoinColumns = @JoinColumn(name = "memberId"))
    private List<Member> members;*/
    @ManyToOne
    private Member member;

}
