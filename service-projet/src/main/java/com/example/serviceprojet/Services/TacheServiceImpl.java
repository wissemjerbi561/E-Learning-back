package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.*;
import com.example.serviceprojet.repository.ActiviteRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import com.example.serviceprojet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacheServiceImpl implements  ITacheService {
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    ActiviteRepository activiteRepository;


    @Override
    public void ajouterTache(Tache tache, Long idActivite) {
        // TODO Auto-generated method stub

        Activite activite = activiteRepository.findById(idActivite).orElse(null);
        //  User User = userRepository.findById(Id).orElse(null);
        tache.setActivite(activite);
        tacheRepository.save(tache);

    }
}