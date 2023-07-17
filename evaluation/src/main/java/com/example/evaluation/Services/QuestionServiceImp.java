package com.example.evaluation.Services;

import com.example.evaluation.Entities.Question;
import com.example.evaluation.Entities.Test;
import com.example.evaluation.Repositories.QuestionRepo;
import com.example.evaluation.Repositories.TestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        if(test.getPoints()>0) {
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
}
