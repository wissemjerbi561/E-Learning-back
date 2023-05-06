package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import com.example.cours.Repositories.ChapitreRepo;
import com.example.cours.Repositories.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapitreServiceImp implements ChapitreService{
    @Autowired
    ChapitreRepo chapitreRepo;
@Autowired
    CoursRepo coursRepo;
    @Override
    public Chapitre saveCh(Chapitre ch) {
        return chapitreRepo.save(ch);
    }

    @Override
    public Chapitre RetrieveCh(Long idch) {
        return chapitreRepo.findById(idch).get();
    }

    @Override
    public List<Chapitre> RetrieveAllCh() {
        List<Chapitre> chs=(List<Chapitre>)chapitreRepo.findAll();
        return chs;
    }

    @Override
    public void AffectCh(Long idch, Long idcr) {
Chapitre ch=chapitreRepo.findById(idch).get();
        Cours cr=coursRepo.findById(idcr).get();
ch.setCoursch(cr);
        chapitreRepo.save(ch);
    }

    @Override
    public void deleteCh(Long idch) {
      Chapitre ch=  chapitreRepo.findById(idch).get();
        chapitreRepo.delete(ch);
    }

    @Override
    public Chapitre saveAffectCh(Chapitre ch) {
         chapitreRepo.save(ch);
        Cours cr=coursRepo.findById(ch.getIdCours()).get();
  ch.setCoursch(cr);
        return  chapitreRepo.save(ch);
    }
}
