package com.example.evaluation.Services;

import com.example.evaluation.Entities.Question;

import java.util.List;

public interface QuestionService {
    public Question saveAffectQuestion(Question q);
    public List<Question> retrieveAll();
}
