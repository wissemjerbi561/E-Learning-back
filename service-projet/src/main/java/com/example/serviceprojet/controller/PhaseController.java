package com.example.serviceprojet.controller;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.PhaseRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phase")
public class PhaseController {
    @Autowired
    PhaseRepository phaseRepository;

    public PhaseController(PhaseRepository phaseRepository) {
        this.phaseRepository = phaseRepository;
    }

    @GetMapping
    public ResponseEntity getAllPhases(){
        return ResponseEntity.ok(this.phaseRepository.findAll());
    }
    @PostMapping("/create-phase")
    public ResponseEntity <Phase>savePhase (@RequestBody Phase phase){
        return ResponseEntity.ok(phaseRepository.save(phase));
    }
    @GetMapping("/{idPhase}")
    public Phase getPhaseById(@PathVariable Long idPhase) {
        return phaseRepository.findById(idPhase).orElse(null);
    }

    @PutMapping(value = "/{idPhase}")
    public void updatePhase(@PathVariable Long idPhase, @RequestBody Phase phase) {
        Phase phase1 = phaseRepository.findById(idPhase).orElse(null);
        if (phase1 != null) {
            phase1.setPhaseType(phase.getPhaseType());


            phaseRepository.save(phase1);
        }
    }
    @DeleteMapping("/{idPhase}")
    public void deletePhaseById(@PathVariable Long idPhase) {
        phaseRepository.deleteById(idPhase);
    }

}
