package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.*;
import com.example.serviceprojet.enumeration.PhaseType;
import com.example.serviceprojet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProjetServiceImp implements IProjetService{
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    AffectationTacheRepository affectationTacheRepository;
    @Autowired
    CoursRepository coursRepository;
    @Autowired
    ProblemeRepository problemeRepository;
    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    TacheRepository tacheRepository;

    @Autowired
    TypePhaseRepository typePhaseRepository;


    @Override
    public void ajouterProjet(Projet projet, Long idCours) {
        // TODO Auto-generated method stub

        Cours cours = coursRepository.findById(idCours).orElse(null);
      //  User User = userRepository.findById(Id).orElse(null);
        projet.setCours(cours);
       projetRepository.save(projet);

    }

    @Override
    public void ajouterType(Type type) {

        typeRepository.save(type);

    }
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Override
    public void ajouterNotification(Notification notification, Long typeId) {
        // TODO Auto-generated method stub

        Type  type = typeRepository.findById(typeId).orElse(null);
        //  User User = userRepository.findById(Id).orElse(null);
        notification.setType(type);
        notificationRepository.save(notification);

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

    public void affecterPhaseProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        TypePhase phasetype = typePhaseRepository.findByOrdre(1);
            Phase phase = new Phase();
            phase.setProjet(projet);
            phase.setDescription("initilialisation");
            phase.setStatus("en cours");
            phase.setTypePhase(phasetype);
        phase.setDateDebut(new Date()); // Ajouter la date système actuelle

        phase.setEtat(true);
            phaseRepository.save(phase);
         //   List<Tache> lstache =tacheRepository.findTachesDuProjet(idProjet);
      //   for (Tache t:lstache){
           //  affectationTacheRepository.save(new AffectationTache(t));
           //  System.out.println("tache ="+ t.getDescription());
       //  }
       //  projetRepository.save(projet);

    }

    public List<Projet> obtenirProjetsStatusFalse() {
        return projetRepository.findByStatusFalse();
    }
    public List<Object[]> obtenirNombreProblemesParProjet() {
        return projetRepository.obtenirNombreProblemesParProjet();
    }
    public void affecterdeuxiemePhaseProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        TypePhase phasetype = typePhaseRepository.findByOrdre(2);
        Phase phaseOrdre1 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 1);

        phaseOrdre1.setStatus("terminé");
        phaseOrdre1.setEtat(false);
        phaseOrdre1.setDateFin(new Date());
        phaseRepository.save(phaseOrdre1);
        Phase phase = new Phase();
        phase.setProjet(projet);
        phase.setDescription("planning");
        phase.setStatus("en cours");
        phase.setTypePhase(phasetype);
        phase.setEtat(true);
        phase.setDateDebut(new Date()); // Ajouter la date système actuelle

        phaseRepository.save(phase);

    }
    public void affectertroixiemePhaseProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        TypePhase phasetype = typePhaseRepository.findByOrdre(3);
        Phase phaseOrdre2 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 2);
        phaseOrdre2.setStatus("terminé");
        phaseOrdre2.setEtat(false);
        phaseOrdre2.setDateFin(new Date());

        phaseRepository.save(phaseOrdre2);
        Phase phase = new Phase();
        phase.setProjet(projet);
        phase.setDescription("realisation");
        phase.setStatus("en cours");
        phase.setTypePhase(phasetype);
        phase.setEtat(true);
        phase.setDateDebut(new Date()); // Ajouter la date système actuelle

        phaseRepository.save(phase);

    }
    public void affecterquateriemePhaseProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        TypePhase phasetype = typePhaseRepository.findByOrdre(4);
        Phase phaseOrdre3 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 3);
        phaseOrdre3.setStatus("terminé");
        phaseOrdre3.setEtat(false);
        phaseOrdre3.setDateFin(new Date());

        phaseRepository.save(phaseOrdre3);
        Phase phase = new Phase();
        phase.setProjet(projet);
        phase.setDescription("evaluate");
        phase.setStatus("en cours");
        phase.setEtat(true);

        phase.setTypePhase(phasetype);
        phase.setDateDebut(new Date()); // Ajouter la date système actuelle

        phaseRepository.save(phase);

    }
    public void affecterdernierPhaseProjet(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        TypePhase phasetype = typePhaseRepository.findByOrdre(5);
        Phase phaseOrdre4 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 4);
        phaseOrdre4.setStatus("terminé");
        phaseOrdre4.setEtat(false);
        phaseOrdre4.setDateFin(new Date());
        phaseRepository.save(phaseOrdre4);
        Phase phase = new Phase();
        phase.setProjet(projet);
        phase.setDescription("experience evaluation");
        phase.setStatus("en cours");
        phase.setEtat(true);
        phase.setTypePhase(phasetype);
        phase.setDateDebut(new Date()); // Ajouter la date système actuelle
       phaseRepository.save(phase);


    }
/*    public void terminerDernierePhase(Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        Phase phaseOrdre5 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 5);
        phaseOrdre5.setStatus("terminé");
        phaseOrdre5.setEtat(false);
        phaseOrdre5.setDateFin(new Date());
        phaseRepository.save(phaseOrdre5);

    }
*/

    //@Override
   // public Projet startProjet(Long idProjet) {
        //Projet projet = projetRepository.findById(idProjet).orElse(null);
       // Phase phase = phaseRepository.findByPhaseType(PhaseType.Initializtion);
      //  projet.setIdPhaseActuelle(phase.getIdPhase());
        //projet.setDemarre(true);

    //    return projetRepository.save(projet);
    ///}


    // ...


    public List<Tache> getTachesDuProjet(Long idProjet) {
        return tacheRepository.findTachesDuProjet(idProjet);
    }
    public List<AffectationTache> getAffectationTachesDuProjet(Long idProjet) {
        return affectationTacheRepository.findAffectationsDuProjet(idProjet);
    }
    public List<Tache> getAffectationTachesnonaffectesDuProjet(Long idProjet) {
        return tacheRepository.findTachesNonAffecteesAuProjet(idProjet);
    }

}





