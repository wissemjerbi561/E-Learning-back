package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Projet;

import java.util.List;


public interface IProjetService {
    public void ajouterProjet(Projet projet, Long idCours);

    Projet startProjet(Long idProjet);


    //   public List<Projet> SearchProjetByDescription(String description);
}
