package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ActiviteServiceImpl;
import com.example.serviceprojet.Services.TacheServiceImpl;
import com.example.serviceprojet.entity.Activite;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.repository.ProjetRepository;
import com.example.serviceprojet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project/tache")
public class TacheController {


    @Autowired
    TacheRepository tacheRepository;
    @Autowired
    TacheServiceImpl tacheService;

    public TacheController(TacheRepository tacheRepository) {
        this.tacheRepository = tacheRepository;
    }

    @GetMapping
    public ResponseEntity getAllTaches() {
        return ResponseEntity.ok(this.tacheRepository.findAll());
    }

    @PostMapping("/create-tache")
    public ResponseEntity<Tache> saveTache(@RequestBody Tache tache) {
        return ResponseEntity.ok(tacheRepository.save(tache));
    }

    @GetMapping("/{idTache}")
    public Tache getTacheById(@PathVariable Long idTache) {
        return tacheRepository.findById(idTache).orElse(null);
    }


    @PutMapping("/{idTache}")
    public ResponseEntity<Tache> updatetache(@PathVariable Long idTache, @RequestBody Tache tache) {
        Tache tache1 = tacheRepository.findById(idTache).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + idTache));
        tache1.setDateDebut(tache.getDateDebut());
        tache1.setDateFin(tache.getDateFin());
        tache1.setDescription(tache.getDescription());
        tache1.setDure(tache.getDure());
        Tache updatetache = tacheRepository.save(tache1);
        return  ResponseEntity.ok(updatetache);
    }
    @PutMapping("/{idTache}/deactiver")
    public ResponseEntity<Tache> deactivertache(@PathVariable Long idTache, @RequestBody Tache tache) {
        Tache tache1 = tacheRepository.findById(idTache).orElseThrow(() -> new ResourceNotFoundException("tache not exist with id" + idTache));
        tache1.setStatus(true); // Désactiver tache

        Tache updateTache = tacheRepository.save(tache1);
        return ResponseEntity.ok(updateTache);
    }

    @DeleteMapping("/" +
            "{idTache}")
    public void deleteTacheById(@PathVariable Long idTache) {
        tacheRepository.deleteById(idTache);
    }

    @PostMapping("/add-tache/{idActivite}")
    public void ajouterTahe(@RequestBody Tache tache, @PathVariable ("idActivite") Long idActivite) {
        tacheService.ajouterTache(tache, idActivite);
    }

    @GetMapping("/withProjet")
    public List<Tache> getTachesWithProjet() {
        return tacheService.getTachesWithProjet();
    }
}