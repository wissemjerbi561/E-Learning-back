package com.membre.membre.Controllers;

import com.membre.membre.Entities.Evaluation;
import com.membre.membre.Entities.Member;
import com.membre.membre.Repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/evaluation")
@CrossOrigin(value = "*")
public class EvaluationController {
    @Autowired
    EvaluationRepository evaluationRepository;

    public EvaluationController(EvaluationRepository evaluationRepository){

        this.evaluationRepository=evaluationRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<Evaluation> saveEvaluation(@RequestBody Evaluation evaluation){

        return ResponseEntity.ok(evaluationRepository.save(evaluation));
    }
    @GetMapping("/evaluations")
    public ResponseEntity getAllEvaluations(){

        return  ResponseEntity.ok(this.evaluationRepository.findAll());
    }
    @GetMapping("/{id}")
    public Evaluation getEvaluationById(@PathVariable Long id){

        return  evaluationRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updateEvaluation(@PathVariable Long id,@RequestBody Evaluation evaluation) {
        Evaluation evaluation1 = evaluationRepository.findById(id).orElse(null);
        if (evaluation1 != null) {
            evaluation1.setEvaluationId(evaluation.getEvaluationId());
            evaluation1.setFinalNote(evaluation.getFinalNote());

            evaluationRepository.save(evaluation1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEvaluationById(@PathVariable Long id){

        evaluationRepository.deleteById(id);
    }

}
