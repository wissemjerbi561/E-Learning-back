package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;

import java.util.List;


public interface IProjetService {
    public void ajouterProjet(Projet projet, Long idCours);

    Projet startProjet(Long idProjet);

    public List<Tache> getTachesDuProjet(Long idProjet);
    //   public List<Projet> SearchProjetByDescription(String description);
}
