package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface TacheRepository  extends JpaRepository<Tache,Long> {
    List<Tache> findByActivite(Activite activite);

}