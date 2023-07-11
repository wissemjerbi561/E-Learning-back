package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Notification;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.entity.Type;

import java.util.List;


public interface IProjetService {
    public void ajouterProjet(Projet projet, Long idCours);

    Projet startProjet(Long idProjet);
    public void ajouterNotification(Notification notification, Long typeId);
    public void ajouterType(Type type);

    public List<Tache> getTachesDuProjet(Long idProjet);
    //   public List<Projet> SearchProjetByDescription(String description);
}
