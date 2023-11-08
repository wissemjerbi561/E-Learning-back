package com.example.cours.Services;

import com.example.cours.Entities.Question;
import com.example.cours.Entities.Test;
import com.example.cours.Repositories.QuestionRepo;
import com.example.cours.Repositories.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService{
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    TestRepo testRepo;

    @Override
    public Question saveAffectQuestion(Question q) {
        Test test=testRepo.findById(q.getIdTest()).get();
        if(test.getPoints()>0 && q.getPointsQuestion()<=test.getPoints()) {
        q.setTest(test);
        test.setPoints(test.getPoints()-q.getPointsQuestion());
        testRepo.save(test);
        return questionRepo.save(q);
        }
        else return null;
    }

    @Override
    public List<Question> retrieveAll() {
        List<Question> questions=(List<Question>)questionRepo.findAll();
        return questions;
    }

    @Override
    public void deleteQuestion(Long idq) {
        questionRepo.deleteById(idq);
    }
    @Override
    public List<Question> getQuestionsByTestId(Long idTest) {
        Test test=testRepo.findById(idTest).get();
        List<Question> questions=(List<Question>)questionRepo.findAll();
        List<Question> questionsParTest=new ArrayList<>();
        for(Question qs:questions){
            if(qs.getTest()==test){
                questionsParTest.add(qs);
            }
        }
        return questionsParTest;
    }
}
