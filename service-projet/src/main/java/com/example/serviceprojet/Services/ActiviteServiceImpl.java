package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.repository.ActiviteRepository;
import com.example.serviceprojet.repository.ProblemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActiviteServiceImpl  implements IActiviteService {
    @Autowired
    ActiviteRepository activiteRepository;
    @Autowired
    ProblemeRepository problemeRepository;

    @Override
    public void ajouterActivite(Activite activite, Long idProbleme) {
        // TODO Auto-generated method stub

        Probleme probleme = problemeRepository.findById(idProbleme).orElse(null);
        if (activite.getDure() > probleme.getDure()) {
            throw new IllegalArgumentException("La durée de l'activité ne peut pas dépasser la durée du problème.");
        }
        //  User User = userRepository.findById(Id).orElse(null);
        activite.setProbleme(probleme);
        activiteRepository.save(activite);

    }
}
