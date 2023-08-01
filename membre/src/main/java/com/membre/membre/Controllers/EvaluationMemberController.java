package com.membre.membre.Controllers;

import com.membre.membre.Entities.EvaluationMember;
import com.membre.membre.Repositories.EvaluationMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/api/member/evaluation")
@CrossOrigin(value = "*")
public class EvaluationMemberController {
    @Autowired
    EvaluationMemberRepository evaluationMemberRepository;

    public EvaluationMemberController(EvaluationMemberRepository evaluationMemberRepository){

        this.evaluationMemberRepository = evaluationMemberRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<EvaluationMember> saveEvaluation(@RequestBody EvaluationMember evaluationMember){

        return ResponseEntity.ok(evaluationMemberRepository.save(evaluationMember));
    }
    @GetMapping("/evaluations")
    public ResponseEntity getAllEvaluations(){

        return  ResponseEntity.ok(this.evaluationMemberRepository.findAll());
    }
    @GetMapping("/{id}")
    public EvaluationMember getEvaluationById(@PathVariable Long id){

        return  evaluationMemberRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updateEvaluation(@PathVariable Long id,@RequestBody EvaluationMember evaluationMember) {
        EvaluationMember evaluationMember1 = evaluationMemberRepository.findById(id).orElse(null);
        if (evaluationMember1 != null) {
            evaluationMember1.setEvaluationId(evaluationMember.getEvaluationId());


            evaluationMemberRepository.save(evaluationMember1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteEvaluationById(@PathVariable Long id){

        evaluationMemberRepository.deleteById(id);
    }

}
