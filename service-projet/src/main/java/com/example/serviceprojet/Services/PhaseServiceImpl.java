package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.PhaseRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PhaseServiceImpl implements IPhaseService {
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    PhaseRepository phaseRepository;

    @Override
    public List<Phase> getPhasesByProjet(Long idProjet) {
        Projet projet=projetRepository.findById(idProjet).orElse(null);
        return phaseRepository.findByProjet(projet);
    }
    public void updatePhase(Long phaseId, Date dateFin, String status) {
        Phase phase = phaseRepository.findById(phaseId).orElse(null);
        if (phase != null) {
            phase.setDateFin(dateFin);
            phase.setStatus(status);
            phaseRepository.save(phase);
        } else {
            throw new IllegalArgumentException("Phase not found");
        }
    }
}


