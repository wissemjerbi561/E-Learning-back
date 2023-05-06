package com.example.cours.Services;

import com.example.cours.Entities.AviCours;
import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Theme;
import com.example.cours.Repositories.AviCoursRepo;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.ThemeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CoursServiceImp implements CoursService{
    @Autowired
    CoursRepo coursRepo;
    @Autowired
    AviCoursRepo aviCoursRepo;
    @Autowired
    ThemeRepo themeRepo;

    @Override
    public Cours saveCours(Cours c) {
        return coursRepo.save(c);
    }

    @Override
    public Cours retrieveCours(Long idCr) {
        return coursRepo.findById(idCr).get();
    }

    @Override
    public List<Cours> retrieveAll() {
        List<Cours> listcr=( List<Cours>)coursRepo.findAll();
        return listcr;
    }

    @Override
    public Cours updateCr(Long idc) {
Cours cr= coursRepo.findById(idc).get();
      return  coursRepo.save(cr);
    }

    @Override
    public void delete(Long idc) {
        Cours cr= coursRepo.findById(idc).get();
        coursRepo.delete(cr);

    }

    @Override
    public void calculNotemoyenne(Long idcr) {
        int noteTotale=0;
        int dividande=0;
        float noteSatisfaction= 0;
        Cours cr=coursRepo.findById(idcr).get();
    List<AviCours> listavis=(List<AviCours>)aviCoursRepo.findAll();
        List<AviCours> listaviCours=new ArrayList<>();
        for (AviCours monAvis : listavis) {
            if(monAvis.getCoursav().equals(cr)){
                listaviCours.add(monAvis);
            }
        }
        for (AviCours monAvis : listaviCours) {
            dividande=dividande+1;
            noteTotale=noteTotale+monAvis.getNoteSatisfaction();
        }
        noteSatisfaction=noteTotale / dividande;
        cr.setNoteMoyenneSatisfaction(noteSatisfaction);
        coursRepo.save(cr);
    }

    @Override
    public Cours saveAffectCr(Cours cr) {
        coursRepo.save(cr);
        Theme th=themeRepo.findById(cr.getIdTheme()).get();
        cr.setTheme(th);
        return  coursRepo.save(cr);
    }

}
