package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.AffectationTache;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.repository.AffectationTacheRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import com.example.serviceprojet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AffectationTacheServiceImpl implements IAffectationTacheService{
    @Autowired
    private AffectationTacheRepository affectationTacheRepository;
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Override
    public AffectationTache ajouterAffectationTache(AffectationTache affectationTache){

        Tache tache = tacheRepository.findById(affectationTache.getTacheId()).orElse(null);
        affectationTache.setTache(tache);
        if ( tache.getDateDebut() == null){
            tache.setDateDebut(new Date());

        }
        // Calcul de la date de fin
        long millisecondsPerDay = 24 * 60 * 60 * 1000; // Nombre de millisecondes dans une journée
        long dureeEnMillisecondes = tache.getDure() * millisecondsPerDay; // Durée en millisecondes

        long dateFinEnMillisecondes = tache.getDateDebut().getTime() + dureeEnMillisecondes; // Calcul de la date de fin en millisecondes
        Date dateFin = new Date(dateFinEnMillisecondes); // Conversion de la date de fin en objet Date
        tache.setDateFin(dateFin);


        affectationTache=affectationTacheRepository.save(affectationTache);
        return affectationTache;
    }
    //public List<AffectationTache> getAffectationsByProjet(Projet projet) {
   //     return affectationTacheRepository.findByProjet(projet);
   // }

    public boolean verifierAffectationExistante(Long tacheId, Long memberId) {
        Tache tache = tacheRepository.findById(tacheId).orElse(null);

        if (tache != null) {
            return affectationTacheRepository.existsByTacheAndMemberId(tache, memberId);
        }

        return false;
    }
}
