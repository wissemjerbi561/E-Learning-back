package com.example.cours.Services;

import com.example.cours.Entities.Choix;

import java.util.List;

public interface ChoixService {
    public Choix saveAffectChoix(Choix c);
    public List<Choix> retrieveAll();
    public List<Choix> retrieveByQuestion(Long QuestionId);
}
