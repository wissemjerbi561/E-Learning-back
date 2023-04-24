package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ProjetServiceImp;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.AttributeNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/projet")
public class ProjetController {
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProblemeRepository problemeRepository;
    @Autowired
    ProjetServiceImp projetService;

    public ProjetController(ProjetRepository projetRepository) {
        this.projetRepository = projetRepository;
    }

    @GetMapping
    public ResponseEntity getAllProjets() {
        return ResponseEntity.ok(this.projetRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Projet> saveProjet(@RequestBody Projet projet) {
        return ResponseEntity.ok(projetRepository.save(projet));
    }

    @GetMapping("/{idProjet}")
    public Projet getProjetById(@PathVariable Long idProjet) {
        return projetRepository.findById(idProjet).orElse(null);
    }





    @PutMapping("/{idProjet}")
    public ResponseEntity<Projet> updateprojet(@PathVariable Long idProjet, @RequestBody Projet projet) {
        Projet projet1 = projetRepository.findById(idProjet).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idProjet));
        projet1.setDateDebut(projet.getDateDebut());
        projet1.setDateFin(projet.getDateFin());
        projet1.setDescription(projet.getDescription());
        Projet updateprojet = projetRepository.save(projet1);
return  ResponseEntity.ok(updateprojet);
    }



    @DeleteMapping("/{idProjet}")
    public void deleteProjetById(@PathVariable Long idProjet) {
        projetRepository.deleteById(idProjet);
    }

    @PostMapping("/add-projet/{idCours}")
    public void ajouterProjet(@RequestBody Projet projet,@PathVariable ("idCours") Long idCours){
        projetService.ajouterProjet(projet, idCours);


    }

    @PostMapping("/add-projett/{idProbleme}")
    public void ajouterProjetprobleme(@RequestBody Projet projet,@PathVariable ("idProbleme") Long idProbleme){
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

    @GetMapping("/{idProjet}/problemes")
    public List<Probleme> getProblemesByProjet(@PathVariable Long idProjet) {
        Projet projet = projetRepository.findById(idProjet)
                .orElseThrow(() -> new NoSuchElementException("Projet non trouvé"));
        return problemeRepository.findByProjet(projet);
    }


}
