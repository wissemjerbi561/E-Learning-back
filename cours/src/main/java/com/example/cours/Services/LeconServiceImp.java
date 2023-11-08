package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Lecon;
import com.example.cours.Repositories.ChapitreRepo;
import com.example.cours.Repositories.LeconRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeconServiceImp implements LeconService{
    @Autowired
    LeconRepo leconRepo;
    @Autowired
    ChapitreRepo chapitreRepo;
    @Override
    public Lecon saveAffectLecon(Lecon l) {
        leconRepo.save(l);
        Chapitre ch=chapitreRepo.findById(l.getIdChapitre()).get();
        l.setChapitre(ch);
        return leconRepo.save(l);
    }

    @Override
    public Lecon retrieveLecon(Long idl) {
        return leconRepo.findById(idl).get();
    }

    @Override
    public List<Lecon> retrieveAll() {
        List<Lecon> lecons=(List<Lecon> )leconRepo.findAll();
        return lecons;
    }

    @Override
    public void delete(Long idl) {
leconRepo.deleteById(idl);
    }

    @Override
    public Lecon updateLecon(Lecon updatedLecon) {
        if (updatedLecon.getId() != null && leconRepo.existsById(updatedLecon.getId())) {
            return leconRepo.save(updatedLecon);
        } else {
            return null;
        }
    }
}
