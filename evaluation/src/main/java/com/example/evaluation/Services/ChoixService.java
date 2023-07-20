package com.example.evaluation.Services;

import com.example.evaluation.Entities.Choix;
import com.example.evaluation.Entities.Question;

import java.util.List;

public interface ChoixService {
    public Choix saveAffectChoix(Choix c);
    public List<Choix> retrieveAll();
    public List<Choix> retrieveByQuestion(Long QuestionId);
}
