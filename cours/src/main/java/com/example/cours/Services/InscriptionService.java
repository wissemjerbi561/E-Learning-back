package com.example.cours.Services;

import com.example.cours.Entities.Inscription;

import java.util.List;

public interface InscriptionService {
    public List<Inscription> RetrieveAllInscriptions();
    public Inscription saveAffectInscription(Inscription ins);
}
