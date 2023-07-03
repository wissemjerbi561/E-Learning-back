package com.example.cours.Services;

import com.example.cours.Entities.AviCours;
import com.example.cours.Entities.Cours;
import com.example.cours.Repositories.AviCoursRepo;
import com.example.cours.Repositories.CoursRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AviCoursServiceImp implements AviCoursService{
    @Autowired
    AviCoursRepo aviCoursRepo;
    @Autowired
    CoursRepo coursRepo;
    @Autowired
    CoursServiceImp coursService;

    @Override
    public AviCours saveAvi(AviCours av) {
        return aviCoursRepo.save(av);
    }

    @Override
    public AviCours RetrieveAvi(Long idAv) {
        return aviCoursRepo.findById(idAv).get();
    }

    @Override
    public List<AviCours> RetrieveAllAvi() {
        List<AviCours> avis=(List<AviCours>)aviCoursRepo.findAll();
        return avis;
    }

    @Override
    public void AffectAvi(Long idAv, Long idCr) {
        AviCours av=aviCoursRepo.findById(idAv).get();
        Cours cr=coursRepo.findById(idCr).get();
        av.setCoursav(cr);
        aviCoursRepo.save(av);
    }

    @Override
    public AviCours saveAffectAv(AviCours ch) {
        aviCoursRepo.save(ch);
        Cours cr=coursRepo.findById(ch.getIdCours()).get();
        ch.setCoursav(cr);
        coursService.calculNotemoyenne(cr.getIdCours());
        return  aviCoursRepo.save(ch);
    }
}

