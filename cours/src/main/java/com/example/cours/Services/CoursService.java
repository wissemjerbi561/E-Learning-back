package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;

import java.util.List;

public interface CoursService {
    public Cours saveCours(Cours c);
    public Cours retrieveCours(Long idCr);
    public List<Cours>  retrieveAll();
    public Cours updateCr(Long idc);
    public void delete(Long idc);
    public void calculNotemoyenne(Long idcr);
    public Cours saveAffectCr(Cours cr);
}
