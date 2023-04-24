package com.example.serviceprojet.controller;

import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.repository.ProjetRepository;
import com.example.serviceprojet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/tache")
public class TacheController {


    @Autowired
    TacheRepository tacheRepository;

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

    @PutMapping(value = "/{idTache}")
    public void updateTache(@PathVariable Long idTache, @RequestBody Tache tache) {
        Tache tache1 = tacheRepository.findById(idTache).orElse(null);
        if (tache1 != null) {
            tache1.setDateDebut(tache.getDateDebut());
            tache1.setDateFin(tache.getDateFin());
            tache1.setPourcentage(tache.getPourcentage());

            tacheRepository.save(tache1);
        }
    }

    @DeleteMapping("/{idTache}")
    public void deleteTacheById(@PathVariable Long idTache) {
        tacheRepository.deleteById(idTache);
    }


}