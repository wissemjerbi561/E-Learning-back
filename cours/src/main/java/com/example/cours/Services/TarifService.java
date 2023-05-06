package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Tarif;

import java.util.List;

public interface TarifService {
    public Tarif RetrieveCh(Long idtr);
    public List<Tarif> RetrieveAllCh();

    public void deleteCh(Long idtr);
    public Tarif saveAffectCh(Tarif tr);
}
