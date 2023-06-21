package com.example.cours.Services;

import com.example.cours.Entities.Inscription;
import com.example.cours.Entities.Session;
import com.example.cours.Repositories.InscriptionRepo;
import com.example.cours.Repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscriptionServiceImp implements InscriptionService{
    @Autowired
    InscriptionRepo inscriptionRepo;
    @Autowired
    SessionRepo sessionRepo;
    @Override
    public List<Inscription> RetrieveAllInscriptions() {
        List<Inscription> inscriptions=(List<Inscription>)inscriptionRepo.findAll();
        return inscriptions;
    }

    @Override
    public Inscription saveAffectInscription(Inscription ins) {
        // inscriptionRepo.save(ins);
        Session ses=sessionRepo.findById(ins.getIdSession()).get();
        if(ses.getCapacite()!=0) {
            ins.setSession(ses);
            ses.setCapacite(ses.getCapacite()-1);
            ses.setNbrDesInscrits(ses.getNbrDesInscrits()+1);
            sessionRepo.save(ses);
            return inscriptionRepo.save(ins);
        }
        else    return null;

    }
}
