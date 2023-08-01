package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.*;
import com.example.serviceprojet.repository.AffectationProjetRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class AffectationProjetServiceImpl implements IAffectationProjetService {

    @Autowired
    private AffectationProjetRepository affectationProjetRepository;
    @Autowired
    ProjetRepository projetRepository;

    @Override
    public AffectationProjet ajouterAffectation(AffectationProjet affectationProjet) {

        Projet projet = projetRepository.findById(affectationProjet.getProjetId()).orElse(null);

        affectationProjet.setProjet(projet);
        affectationProjet.setRole("Tuteur Professionnel");
        affectationProjet.setExisting(true);

        affectationProjet=affectationProjetRepository.save(affectationProjet);
        return affectationProjet;

    }
    public AffectationProjet ajouterAffectationtuteuracademique(AffectationProjet affectationProjet) {

        Projet projet = projetRepository.findById(affectationProjet.getProjetId()).orElse(null);

        affectationProjet.setProjet(projet);
        affectationProjet.setRole("Tuteur Academique");

        affectationProjet=affectationProjetRepository.save(affectationProjet);
        return affectationProjet;

    }
    public AffectationProjet ajouterAffectationApprenantVerification(AffectationProjet affectationProjet) {

        Projet projet = projetRepository.findById(affectationProjet.getProjetId()).orElse(null);

        affectationProjet.setProjet(projet);
        affectationProjet.setRole("Apprenant verification");


        affectationProjet=affectationProjetRepository.save(affectationProjet);
        return affectationProjet;

    }
    public AffectationProjet ajouterAffectationApprenantDaide(AffectationProjet affectationProjet) {

        Projet projet = projetRepository.findById(affectationProjet.getProjetId()).orElse(null);

        affectationProjet.setProjet(projet);
        affectationProjet.setRole("Apprenant d'aide");

        affectationProjet=affectationProjetRepository.save(affectationProjet);
        return affectationProjet;

    }





}