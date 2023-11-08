package com.example.cours.Services;

import com.example.cours.Entities.Question;

import java.util.List;

public interface QuestionService {
    public Question saveAffectQuestion(Question q);
    public List<Question> retrieveAll();
    public void deleteQuestion(Long idq);
    public List<Question> getQuestionsByTestId(Long idTest);
}
