package com.example.evaluation.Controllers;

import com.example.evaluation.Entities.Question;
import com.example.evaluation.Entities.Test;
import com.example.evaluation.Services.QuestionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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
    @DeleteMapping("/supprimerquestion/{idq}")
    public void supprimerqs(@PathVariable Long idq){
        questionService.deleteQuestion(idq);
    }
    @GetMapping("/affichertoutQuestion/{idTest}")
    public List<Question> affichertoutqsByTest(@PathVariable Long idTest){
       return questionService.getQuestionsByTestId(idTest);}
}
