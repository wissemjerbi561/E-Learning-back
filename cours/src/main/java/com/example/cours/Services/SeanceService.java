package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Seance;

import java.util.List;

public interface SeanceService {
    public Seance savese(Seance s);
    public Seance RetrieveSe(Long idse);
    public List<Seance> RetrieveAllse();

    public void deleteSc(Long ids);
    public Seance saveAffectSc(Seance sc);
}
