package com.example.cours.Services;

import com.example.cours.Entities.AviCours;
import com.example.cours.Entities.Chapitre;

import java.util.List;

public interface AviCoursService {
    public AviCours saveAvi(AviCours av);
    public AviCours RetrieveAvi(Long idAv);
    public List<AviCours> RetrieveAllAvi();
    public void AffectAvi(Long idAv,Long idCr);
    public AviCours saveAffectAv(AviCours ch);
}
