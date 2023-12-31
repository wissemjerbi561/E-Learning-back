package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoursService {
    public Cours getCourseById(Long idc);
    public Cours saveCours(Cours c);
    public Cours retrieveCours(Long idCr);
    public List<Cours>  retrieveAll();
    public Cours updateCr(Long idc);
    public void delete(Long idc);
    public void calculNotemoyenne(Long idcr);
    public Cours saveAffectCr(Cours cr, MultipartFile imageFile);
    public Cours saveAffectCrSi(Cours cr);
    public Cours updateCours(Cours updatedCours);
    public Cours logicDeleteCours(Long idcr);
}
