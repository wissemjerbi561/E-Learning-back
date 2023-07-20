package com.example.evaluation.Services;

import com.example.evaluation.Entities.Choix;
import com.example.evaluation.Entities.Question;
import com.example.evaluation.Entities.Test;
import com.example.evaluation.Entities.Type;
import com.example.evaluation.Repositories.ChoixRepo;
import com.example.evaluation.Repositories.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChoixServiceImp implements ChoixService{
    @Autowired
    ChoixRepo choixRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Override
    public Choix saveAffectChoix(Choix c) {
        Question ques=questionRepo.findById(c.getIdQuestion()).get();
        if(ques.getTest().getType()== Type.QCM)
        {
         c.setQuestion(ques);
         return choixRepo.save(c);
        }
        else {
            List<Choix> choix=(List<Choix>)choixRepo.findAll();
            List<Choix>choixQuestion=new ArrayList<>();
            for(Choix ch:choix){
                if(ch.getQuestion()==ques ){
                    choixQuestion.add(ch);
                }
            }
             boolean choixCorrectExiste=false;
            for (Choix chq : choixQuestion) {
                if (chq.isCorrection()==true) {
                    choixCorrectExiste = true;
                    break;
                }
            }
            if (choixCorrectExiste==true && c.isCorrection() ) {



                return null;
            }
            else

            {c.setQuestion(ques);
                return choixRepo.save(c);}


        }

    }

    @Override
    public List<Choix> retrieveAll() {
        List<Choix> choix=(List<Choix>)choixRepo.findAll();
        return choix;
    }

    @Override
    public List<Choix> retrieveByQuestion(Long QuestionId) {
        Question question=questionRepo.findById(QuestionId).get();
        List<Choix> choixes=(List<Choix>)choixRepo.findAll();
        List<Choix> choixParQuestion=new ArrayList<>();
        for(Choix ch:choixes){
            if(ch.getQuestion()==question){
                choixParQuestion.add(ch);
            }
        }
        return choixParQuestion;
    }
    }

