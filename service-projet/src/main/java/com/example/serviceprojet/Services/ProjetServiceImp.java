package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Cours;
import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.enumeration.PhaseType;
import com.example.serviceprojet.repository.CoursRepository;
import com.example.serviceprojet.repository.PhaseRepository;
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
    @Autowired
    PhaseRepository phaseRepository;


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

    @Override
    public Projet startProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        Phase phase = new Phase();
        phase.setPhaseType(PhaseType.Initializtion);
        phase.setProjet(projet);
        phaseRepository.save(phase);

        projet.setIdPhaseActuelle(phase.getIdPhase());
        projet.setDemarre(true);
        return projetRepository.save(projet);
    }


    //@Override
   // public Projet startProjet(Long idProjet) {
        //Projet projet = projetRepository.findById(idProjet).orElse(null);
       // Phase phase = phaseRepository.findByPhaseType(PhaseType.Initializtion);
      //  projet.setIdPhaseActuelle(phase.getIdPhase());
        //projet.setDemarre(true);

    //    return projetRepository.save(projet);
    ///}





}
