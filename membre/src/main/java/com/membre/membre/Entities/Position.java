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
@Table(name = "position")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long posisionId;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "position_id",referencedColumnName = "id")
    private Member member;
}
