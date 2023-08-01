package com.example.evaluation.Controllers;

import com.example.evaluation.Entities.Choix;
import com.example.evaluation.Entities.ChoixUpdateRequest;
import com.example.evaluation.Entities.Question;
import com.example.evaluation.Entities.Test;
import com.example.evaluation.Repositories.TestRepo;
import com.example.evaluation.Services.TestServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api")
public class TestApprenantController {
    @Autowired
    TestServiceImp testService;
    @Autowired
    TestRepo testRepo;
    @Autowired
    private ScheduledExecutorService executorService;

    @PostMapping("/test/{id}/pass")
    public ResponseEntity<String> passTest(@PathVariable("id") Long idTest, @RequestBody List<ChoixUpdateRequest> choixUpdates) {
        Test test = testService.passTest(idTest, choixUpdates);
        if (test != null) {
            return ResponseEntity.ok("Test mis à jour avec succès !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void processTestJson(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Test test = objectMapper.readValue(json, Test.class);

            // Vous avez maintenant l'objet Test désérialisé avec ses questions et choix
            // Vous pouvez accéder aux propriétés de Test, Question et Choix comme suit :

            System.out.println("ID du Test : " + test.getId());
            System.out.println("Titre du Test : " + test.getTitre());

            for (Question question : test.getQuestion()) {
                System.out.println("ID de la Question : " + question.getId());
                System.out.println("Texte de la Question : " + question.getQuestion());

                for (Choix choix : question.getChoix()) {
                    System.out.println("ID du Choix : " + choix.getId());
                    System.out.println("Texte du Choix : " + choix.getText());
                    System.out.println("Est-ce une correction : " + choix.isCorrection());
                    System.out.println("Choix de l'apprenant : " + choix.getChoixApprenant());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/startTest/{testId}")
    public ResponseEntity<String> startTest(@PathVariable Long testId, HttpServletRequest request) {
        // Récupérer le test à partir de la base de données (vous pouvez utiliser TestRepository pour cela)
        Test test = testRepo.findById(testId).orElse(null);

        if (test != null) {
            HttpSession session = request.getSession();
            session.setAttribute("test", test);
            session.setMaxInactiveInterval(test.getDuree() * 60); // Définir la durée de la session en secondes

            // Vous pouvez également utiliser un mécanisme de planification ou de délai pour
            // enregistrer les résultats du test une fois la durée écoulée.
            executorService.schedule(() -> endTest(request), test.getDuree(), TimeUnit.MINUTES);

            return ResponseEntity.ok("Test commencé !");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Test non trouvé !");
        }
    }



    private void endTest(HttpServletRequest request) {
        // Récupérer les résultats du test à partir de la session et enregistrez-les dans la base de données ou un autre système de stockage
        // ...
        request.getSession().invalidate(); // Terminer la session de l'utilisateur
    }

    @PostMapping("/testsession/{id}/pass")
    public ResponseEntity<String> passTestSession(@PathVariable("id") Long idTest, @RequestBody List<ChoixUpdateRequest> choixUpdates) {
        // Récupérer la session de l'utilisateur
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        HttpSession session = request.getSession();

        // Utiliser Spring Session pour gérer la session de l'utilisateur
        Test test = testService.passTestWithSession(idTest, choixUpdates, session);

        if (test != null) {
            return ResponseEntity.ok("Test mis à jour avec succès !");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/testCorrection/{id}")
    public Test passTest(@PathVariable("id") Long idTest){
        return testService.corresctionTest(idTest);
    }

}