package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Cours;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.CoursRepository;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetServiceImp implements IProjetService{
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    CoursRepository coursRepository;
    @Autowired
    ProblemeRepository problemeRepository;

    @Override
    public void ajouterProjet(Projet projet, Long idCours) {
        // TODO Auto-generated method stub

        Cours cours = coursRepository.findById(idCours).orElse(null);
      //  User User = userRepository.findById(Id).orElse(null);
        projet.setCours(cours);
       projetRepository.save(projet);

    }
    public List<Projet> getProjetsWithProblemes() {
        return projetRepository.findAllFetchProblemes();
    }







}
