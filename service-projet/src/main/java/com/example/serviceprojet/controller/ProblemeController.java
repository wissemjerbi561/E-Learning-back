package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ProblemeServiceImpl;
import com.example.serviceprojet.Services.ProjetServiceImp;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")

@RequestMapping("/probleme")


public class ProblemeController {

    @Autowired
    ProblemeRepository problemeRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProblemeServiceImpl problemeService;
    public ProblemeController(ProblemeRepository problemeRepository) {
        this.problemeRepository = problemeRepository;
    }


    @GetMapping
    public ResponseEntity getAllProblems(){
        return ResponseEntity.ok(this.problemeRepository.findAll());
    }
    @PostMapping("/createprobleme")
    public ResponseEntity <Probleme>saveProbleme (@RequestBody Probleme probleme){
        return ResponseEntity.ok(problemeRepository.save(probleme));
    }
    @GetMapping("/{idProbleme}")
    public Probleme getProblemeById(@PathVariable Long idProbleme) {
        return problemeRepository.findById(idProbleme).orElse(null);
    }

    @PutMapping(value = "/{idProbleme}")
    public void updateProjet(@PathVariable Long idProbleme, @RequestBody Probleme probleme) {
        Probleme probleme1 = problemeRepository.findById(idProbleme).orElse(null);
        if (probleme1 != null) {
            probleme1.setDescription(probleme.getDescription());


            problemeRepository.save(probleme1);
        }
    }
    @DeleteMapping("/{idProbleme}")
    public void deleteProblemetById(@PathVariable Long idProbleme) {
        problemeRepository.deleteById(idProbleme);
    }


    @PostMapping("/add-probleme/{idProjet}")
    public void ajouterEtaffecterListe(@RequestBody Probleme probleme,@PathVariable ("idProjet") Long idProjet) {
        //probleme.setDescription( BadWordFilter.getCensoredText(comment.getContents() ));
        problemeService.ajouterEtaffecterListeProbleme(probleme,idProjet);
    }

   // @GetMapping("/FindProblemeByProjet/{idProjet}")
   // @ResponseBody
   // List<Probleme> retrieveProblemesByProjet(@PathVariable Long idProjet) {
    //    return problemeService.retrieveProblemesByProjet(idProjet);
   // }
   @GetMapping("/problemes/{idProjet}")
   public List<Probleme> getProblemes(@PathVariable("idProjet") Long idProjet) {
       Optional<Projet> projetOptional = projetRepository.findById(idProjet);
       if (projetOptional.isPresent()) {
           return new ArrayList<>(projetOptional.get().getProblemes());
       }
       return new ArrayList<>();
   }
    @GetMapping("/listeProblemes/{idProjet}")
    @ResponseBody
    List<Probleme> getProblemeByProjet(@PathVariable("idProjet") Long idProjet) {
        return problemeService.getProbemeByProjet(idProjet);
    }

    }

