package com.example.cours.Services;

import com.example.cours.Entities.AviCours;
import com.example.cours.Entities.Chapitre;

import java.util.List;

public interface ChapitreService {
    public Chapitre saveCh(Chapitre ch);
    public Chapitre RetrieveCh(Long idch);
    public List<Chapitre> RetrieveAllCh();
    public void AffectCh(Long idch,Long idcr);
    public void deleteCh(Long idch);
    public Chapitre saveAffectCh(Chapitre ch);
}
