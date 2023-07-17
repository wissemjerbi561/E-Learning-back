package com.example.evaluation.Controllers;

import com.example.evaluation.Entities.Question;
import com.example.evaluation.Entities.Test;
import com.example.evaluation.Services.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class QuestionController {
    @Autowired
    QuestionServiceImp questionService;
    @PostMapping("/ajoutQuestion")
    public Question ajouterqs(@RequestBody Question qs){
        return questionService.saveAffectQuestion(qs);
    }
    @GetMapping("/affichertoutQuestion")
    public List<Question> affichertoutqs(){
        return questionService.retrieveAll();
    }
}
