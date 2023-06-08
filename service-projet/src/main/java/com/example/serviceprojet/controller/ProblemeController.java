package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.FileService;
import com.example.serviceprojet.Services.FilesStoragesService;
import com.example.serviceprojet.Services.ProblemeServiceImpl;
import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.ResponseMessage;
import com.example.serviceprojet.repository.ActiviteRepository;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
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
    ActiviteRepository activiteRepository;
    @Autowired
    ProblemeServiceImpl problemeService;
    @Autowired
    FileService fileService;
    @Autowired
    FilesStoragesService storageService;

    public ProblemeController(ProblemeRepository problemeRepository) {
        this.problemeRepository = problemeRepository;
    }




    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);

            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }
    @PostMapping("/uploadfilee")
    public void uploadflow(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException {
        problemeService.uploadFile(file);



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

    @PutMapping("/{idProbleme}")
    public ResponseEntity<Probleme> updateprobleme(@PathVariable Long idProbleme, @RequestBody Probleme probleme) {
        Probleme probleme1 = problemeRepository.findById(idProbleme).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idProbleme));
        probleme1.setDateDebut(probleme.getDateDebut());
        probleme1.setDateFin(probleme.getDateFin());
        probleme1.setDescription(probleme.getDescription());
        Probleme updateprobleme = problemeRepository.save(probleme1);
        return  ResponseEntity.ok(updateprobleme);
    }

    ///archiver
    @PutMapping("/{idProbleme}/deactiver")
    public ResponseEntity<Probleme> deactiverprobleme(@PathVariable Long idProbleme, @RequestBody Probleme probleme) {
        Probleme probleme1 = problemeRepository.findById(idProbleme).orElseThrow(() -> new ResourceNotFoundException("Probleme not exist with id" + idProbleme));
        probleme1.setStatus(true); // Désactiver le probleme

        Probleme updateprobleme = problemeRepository.save(probleme1);
        return ResponseEntity.ok(updateprobleme);
    }
    @GetMapping("/problemes/statusFalse")
    public List<Probleme> obtenirProblemesStatusFalse() {
        return problemeService.obtenirProblemesStatusFalse();
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
   /* @PostMapping("/add-probleme/{idProjet}")
    public void ajouterEtaffecterListe(@RequestBody Probleme probleme,@PathVariable ("idProjet") Long idProjet, @RequestParam("file") MultipartFile file) {
        //probleme.setDescription( BadWordFilter.getCensoredText(comment.getContents() ));
        if (file != null && !file.isEmpty()) {
            try {
                byte[] fichier = IOUtils.toByteArray(file.getInputStream());
                probleme.setFichier(fichier);
            } catch (IOException e) {
                // Gérez l'exception
            }
        }
        problemeService.ajouterEtaffecterListeProbleme(probleme,idProjet);
    }*/

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

    @GetMapping("/{idProbleme}/activites")
    public List<Activite> getActivitesByProbleme(@PathVariable Long idProbleme) {
        Probleme probleme = problemeRepository.findById(idProbleme)
                .orElseThrow(() -> new NoSuchElementException("probleme non trouvé"));
        List<Activite> activiteList = activiteRepository.findByStatusFalse();

        return activiteList;
    }

    }

