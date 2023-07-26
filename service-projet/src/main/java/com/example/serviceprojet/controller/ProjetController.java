package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.NotificationServiceImp;
import com.example.serviceprojet.Services.ProjetServiceImp;
import com.example.serviceprojet.Services.TypeServiceImp;
import com.example.serviceprojet.entity.*;
import com.example.serviceprojet.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project/projet")
public class ProjetController {

    @Autowired
    CoursRepository coursRepository;
    @Autowired
    NotificationRepository notificationRepository;
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProblemeRepository problemeRepository;
    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    ProjetServiceImp projetService;
    @Autowired
    TypePhaseRepository typePhaseRepository;


    public ProjetController(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public ResponseEntity getAllProjets() {
        return ResponseEntity.ok(this.projetRepository.findAll());
    }
    @GetMapping("/p")
    public ResponseEntity getAllProjetss() {
        return ResponseEntity.ok(this.projetRepository.findProjetsWithPhasesStatusEnCours());
    }


    @PostMapping("/crea")
    public ResponseEntity<Type> saveType(@RequestBody Type type) {
        return ResponseEntity.ok(typeRepository.save(type));
    }
    @PostMapping("/createType")
    public void ajouterType(@RequestBody Type type ) {
        projetService.ajouterType(type);


    }

    @PostMapping("/create")
    public ResponseEntity<Projet> saveProjet(@RequestBody Projet projet) {
        return ResponseEntity.ok(projetRepository.save(projet));
    }

    @GetMapping("/{idProjet}")
    public Projet getProjetById(@PathVariable Long idProjet) {
        return projetRepository.findById(idProjet).orElse(null);
    }

    ///////////////liste des projet qui ne sont pas deactiver
    @GetMapping("/projets/statusFalse")
    public List<Projet> obtenirProjetsStatusFalse() {
        return projetService.obtenirProjetsStatusFalse();
    }
    @GetMapping("/{idProjet}/phasesss")
    public List<Phase> getPhasesWithStatusFalse(@PathVariable("idProjet") Long idProjet) {
        return phaseRepository.findPhasesWithStatusEn_coursByProjetId(idProjet);
    }
    @GetMapping("/projets/pp")
    public Page<Projet> getListeProjetsAvecPhases(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("idProjet").descending());
        Page<Projet> projets = projetRepository.findAll(pageable);

        projets.forEach(projet -> {
            List<Phase> phases = projet.getPhases();

            if (!phases.isEmpty()) {
                // Trier les phases par ordre décroissant des ID de phase
                Collections.sort(phases, Comparator.comparingLong(Phase::getIdPhase).reversed());

                // Récupérer la dernière phase après le tri
                Phase dernierePhase = phases.get(0);
                projet.setPhases(Collections.singletonList(dernierePhase));
            } else {
                projet.setPhases(null);
            }
        });

        return projets;
    }











    @PutMapping("/{idProjet}")
    public ResponseEntity<Projet> updateprojet(@PathVariable Long idProjet, @RequestBody Projet projet) {
        Projet projet1 = projetRepository.findById(idProjet).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idProjet));
        projet1.setDateDebut(projet.getDateDebut());
        projet1.setDateFin(projet.getDateFin());
        projet1.setDescription(projet.getDescription());
        Projet updateprojet = projetRepository.save(projet1);
        return ResponseEntity.ok(updateprojet);
    }

    @PutMapping("/{idProjet}/deactiver")
    public ResponseEntity<Projet> deactiverprojet(@PathVariable Long idProjet, @RequestBody Projet projet) {
        Projet projet1 = projetRepository.findById(idProjet).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idProjet));
        projet1.setStatus(true); // Désactiver le projet

        Projet updateprojet = projetRepository.save(projet1);
        return ResponseEntity.ok(updateprojet);
    }


    @DeleteMapping("/{idProjet}")
    public void deleteProjetById(@PathVariable Long idProjet) {
        projetRepository.deleteById(idProjet);
    }

    @PostMapping("/add-projet/{idCours}")
    public void ajouterProjet(@RequestBody Projet projet, @PathVariable("idCours") Long idCours) {
        projetService.ajouterProjet(projet, idCours);


    }
    @PostMapping("/add-notification/{typeId}")
    public void ajouterNotification(@RequestBody Notification notification, @PathVariable("typeId") Long typeId) {
        projetService.ajouterNotification(notification, typeId);


    }
    @GetMapping("/getnot")
    public ResponseEntity getAllNotifications() {
        return ResponseEntity.ok(this.notificationRepository.findAll());
    }
    @PostMapping("/add-projett/{idProbleme}")
    public void ajouterProjetprobleme(@RequestBody Projet projet, @PathVariable("idProbleme") Long idProbleme) {
        projetService.ajouterProjet(projet, idProbleme);


    }

    @GetMapping("/problemes/{idProjet}")
    public List<Probleme> getProblemes(@PathVariable("idProjet") Long idProjet) {
        Optional<Projet> projetOptional = projetRepository.findById(idProjet);
        if (projetOptional.isPresent()) {
            return new ArrayList<>(projetOptional.get().getProblemes());
        }
        return new ArrayList<>();
    }


    @GetMapping("/problemesprojet")
    public List<Projet> getProjetsWithProblemes() {
        return projetService.getProjetsWithProblemes();
    }
// list of problems of the project where status is false ( non déactiver)
    @GetMapping("/{idProjet}/problemes")
        public List<Probleme> getProblemesWithStatusFalse(@PathVariable("idProjet") Long idProjet) {
            return problemeRepository.findProblemesWithStatusFalseByProjetId(idProjet);
        }



    @GetMapping("/count/activeproject")
    public int countProjectsByStatus() {
        return projetRepository.countProjectsByStatusFalse();
    }

    @GetMapping("/nombreProblemes")
    public ResponseEntity<List<Object[]>> obtenirNombreProblemesParProjet() {
        List<Object[]> result = projetService.obtenirNombreProblemesParProjet();
        return ResponseEntity.ok(result);
    }
    @GetMapping("/count/notactiveproject")
    public int countnotactiveProjectsByStatus() {
        return projetRepository.countProjectsByStatusTrue();
    }
    @GetMapping("/count/nombretotalprojet")
    public int countnombretotalprojet() {
        return projetRepository.getNombreTotalProjets();
    }
    @GetMapping("listprojetavecphase")
    public List<Object[]> getProjetsWithTypePhases() {
         return projetRepository.findProjetsWithTypePhasesAndDates();



    }


    @GetMapping("/count/{memberId}")
    public int countProjetsByMember(@PathVariable int memberId) {
        return projetRepository.countProjetsByMemberId(memberId);
    }


    @GetMapping("/{idProjet}/phases")
    public List<Phase> getPhasesByProjet(@PathVariable Long idProjet) {
        Projet projet = projetRepository.findById(idProjet)
                .orElseThrow(() -> new NoSuchElementException("Projet non trouvé"));
        return phaseRepository.findByProjet(projet);
    }

    @PutMapping("/{idProjet}/start")
    public ResponseEntity<Projet> startProjet(@PathVariable Long idProjet) {
        Projet projet = projetService.startProjet(idProjet);
        if (projet == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(projet);
    }

    @PostMapping("/{idProjet}/affecterPhase")
    public void affecterPhaseProjet(@PathVariable Long idProjet) {
        Projet projet1 = projetRepository.findById(idProjet).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idProjet));

        projet1.setDemarre(true);
        projetRepository.save(projet1);

        projetService.affecterPhaseProjet(idProjet);


    }
    @GetMapping("/parMembre/{memberId}")
    public List<Projet> getProjetByidUser(@PathVariable Integer memberId) {
        return projetRepository.findProjetByMemberId(memberId);
    }
    @GetMapping("/search/{description}")
    public List<Projet> searchProjectsByDescription(@PathVariable String description) {
        return projetRepository.findByDescriptionContainingIgnoreCase(description);
    }

    @PostMapping("/{idProjet}/affecterPhase2")
    public void affecterdeuxiemePhaseProjet(@PathVariable Long idProjet) {

        projetService.affecterdeuxiemePhaseProjet(idProjet);

    }

    @PostMapping("/{idProjet}/affecterPhase3")
    public void affectertoixiemePhaseProjet(@PathVariable Long idProjet) {

        projetService.affectertroixiemePhaseProjet(idProjet);

    }

    @PostMapping("/{idProjet}/affecterPhase4")
    public void affecterquateriemePhaseProjet(@PathVariable Long idProjet) {

        projetService.affecterquateriemePhaseProjet(idProjet);

    }

    @PostMapping("/{idProjet}/affecterPhase5")
    public void affecterdernierPhaseProjet(@PathVariable Long idProjet) {

        projetService.affecterdernierPhaseProjet(idProjet);

    }

    /* @PutMapping("/{idProjet}/terminerDernierePhase")
     public void terminerDernierePhase(@PathVariable Long idProjet) {
         Phase dernierePhase = projetService.affecterdernierPhaseProjet(idProjet);
         // Mettez à jour la date de fin et le statut de la dernière phase
         dernierePhase.setDateFin(new Date());
         dernierePhase.setStatus("terminé");
         phaseRepository.save(dernierePhase);
     }*/
   /* @PutMapping ("/{idProjet}/terminerDernierePhase")
    public void terminerDernierePhase(@PathVariable Long idProjet) {
        projetService.terminerDernierePhase(idProjet);
    }
*/
    @PutMapping("/{idProjet}/terminerDernierePhaseee")
    public void terminerDernierePhaseee(@PathVariable Long idProjet) {
        Projet projet = projetRepository.findById(idProjet).orElse(null);
        if (projet == null) {
            // Gérer le cas où le projet n'existe pas
            return;
        }

        Phase phaseOrdre5 = phaseRepository.findByProjetAndTypePhaseOrdre(projet, 5);
        if (phaseOrdre5 == null) {
            // Gérer le cas où la phase avec l'ordre 5 n'existe pas
            return;
        }

        phaseOrdre5.setStatus("terminé");
        phaseOrdre5.setEtat(false);
        phaseOrdre5.setDateFin(new Date());
        phaseRepository.save(phaseOrdre5);
    }


    @GetMapping("/{idProjet}/taches")
    public List<Tache> getTachesDuProjet(@PathVariable Long idProjet) {
        return projetService.getTachesDuProjet(idProjet);
    }

    @GetMapping("/{idProjet}/affectationtaches")
    public List<AffectationTache> getAfeectationTachesduProjet(@PathVariable Long idProjet) {
        return projetService.getAffectationTachesDuProjet(idProjet);
    }



    @GetMapping("/{idProjet}/tachesnonaffectes")
    public List<Tache> getTachesnonaffectesduProjet(@PathVariable Long idProjet) {
        return projetService.getAffectationTachesnonaffectesDuProjet(idProjet);
    }

    @GetMapping("/parMembree/{memberId}")
    public List<Cours> getCoursByMemberId(@PathVariable Integer memberId) {
        return coursRepository.findCoursByMemberId(memberId);
    }

    @PostMapping("/rateCourse/{courseId}")
    public void rateCourse(@PathVariable("courseId") Long courseId, @RequestParam("rating") int rating) {
        Optional<Cours> optionalCours = coursRepository.findById(courseId);
        if (optionalCours.isPresent()) {
            Cours cours = optionalCours.get();
            cours.setRating(rating);
            coursRepository.save(cours);
        } else {
            //  le cas où le cours n'existe pas
        }
    }
}
