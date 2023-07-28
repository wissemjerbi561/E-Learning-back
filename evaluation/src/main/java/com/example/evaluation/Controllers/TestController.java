package com.example.evaluation.Controllers;

import com.example.evaluation.Entities.Test;
import com.example.evaluation.Services.TestServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    @Autowired
    TestServiceImp testService;
    @PostMapping("/ajoutTest")
    public Test ajouterts(@RequestBody Test ts){
        return testService.saveTest(ts);
    }
    @GetMapping("/afficheTest/{idt}")
    public Test afficherts(@PathVariable Long idt){
        return testService.retrieveTest(idt);
    }
    @GetMapping("/affichertoutTests")
    public List<Test> affichertoutts(){
        return testService.retrieveAll();
    }
    @PutMapping("/modifiertest/{idt}")
    public Test modifierts(@PathVariable Long idt){
        return testService.updatets(idt);
    }
    @DeleteMapping("/supprimertest/{idt}")
    public void supprimerts(@PathVariable Long idt){
        testService.delete(idt);
    }
}
