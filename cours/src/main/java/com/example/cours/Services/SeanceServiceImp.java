package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Seance;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.SeanceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeanceServiceImp implements SeanceService{
    @Autowired
    SeanceRepo seanceRepo;
    @Autowired
    CoursRepo coursRepo;

    @Override
    public Seance savese(Seance s) {
        return seanceRepo.save(s);
    }

    @Override
    public Seance RetrieveSe(Long idse) {
        return seanceRepo.findById(idse).get();
    }

    @Override
    public List<Seance> RetrieveAllse() {
        List<Seance> seances=(List<Seance>)seanceRepo.findAll();
        return seances;
    }

    @Override
    public void Affectse(Long ids, Long idcr) {
Seance s =seanceRepo.findById(ids).get();
        Cours cr=coursRepo.findById(idcr).get();
        s.setCourssc(cr);
        seanceRepo.save(s);
    }

    @Override
    public void deleteSc(Long ids) {
        Seance s =seanceRepo.findById(ids).get();
        seanceRepo.delete(s);
    }

    @Override
    public Seance saveAffectSc(Seance sc) {
        seanceRepo.save(sc);
        Cours cr=coursRepo.findById(sc.getIdCours()).get();
        sc.setCourssc(cr);
        return   seanceRepo.save(sc);
    }
}
