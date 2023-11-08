package com.example.cours.Services;

import com.example.cours.Entities.*;
import com.example.cours.Repositories.ChoixRepo;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.QuestionRepo;
import com.example.cours.Repositories.TestRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Service
public class TestServiceImp implements TestService{
    @Autowired
    TestRepo testRepo;
    @Autowired
    QuestionRepo questionRepo;
    @Autowired
    ChoixRepo choixRepo;
    @Autowired
    CoursRepo coursRepo;
    @Override
    public Test saveTest(Test t) {
        testRepo.save(t);
        Cours cours=coursRepo.findById(t.getIdCours()).get();
        t.setCourst(cours);
   return testRepo.save(t);
    }

    @Override
    public Test retrieveTest(Long idt) {
        return testRepo.findById(idt).get();
    }

    @Override
    public List<Test> retrieveAll() {
        List<Test> tests=(List<Test>)testRepo.findAll();
        return tests;
    }

    @Override
    public Test updatets(Long idt) {
        Test test=testRepo.findById(idt).get();
        return testRepo.save(test);
    }

    @Override
    public void delete(Long idt) {
testRepo.deleteById(idt);
    }

    /*@Override
    public Test passTest(Long idt) {
        Test test =testRepo.findById(idt).get();
        Set<Question> questionsParTest=new HashSet<>();
        questionsParTest =test.getQuestion();
        for(Question qs:questionsParTest){
            Set<Choix> choixParQuestion=new HashSet<>();
            choixParQuestion=qs.getChoix();
            for (Choix ch:choixParQuestion){

                choixRepo.save(ch);
            }
        }
        return testRepo.save(test);
    }*/
    @Override
    public Test passTest(Long idt, List<ChoixUpdateRequest> choixUpdates) {
        Test test = testRepo.findById(idt).orElse(null);
        if (test == null) {

            return null;
        }

        Set<Question> questionsParTest = test.getQuestion();
        for (Question qs : questionsParTest) {
            Set<Choix> choixParQuestion = qs.getChoix();
            for (Choix ch : choixParQuestion) {
                for (ChoixUpdateRequest updateRequest : choixUpdates) {
                    if (ch.getId().equals(updateRequest.getId())) {
                        ch.setChoixApprenant(updateRequest.isChoixApprenant());
                        choixRepo.save(ch);
                        break;
                    }
                }
            }
        }

        return testRepo.save(test);
    }

    @Override
    public Test passTestWithSession(Long idTest, List<ChoixUpdateRequest> choixUpdates, HttpSession session) {
        Test test = testRepo.findById(idTest).orElse(null);

        if (test != null && !session.isNew()) {
            // Vérifier si le temps écoulé dépasse la durée spécifiée
            Instant startTime = (Instant) session.getAttribute("startTime");
            if (startTime == null) {
                // Si le test commence maintenant, enregistrez l'heure de début dans la session
                startTime = Instant.now();
                session.setAttribute("startTime", startTime);
            } else {
                // Vérifier si le temps écoulé depuis le début du test dépasse la durée spécifiée
                Instant currentTime = Instant.now();
                long elapsedTimeSeconds = currentTime.minusMillis(startTime.toEpochMilli()).getEpochSecond();
                if (elapsedTimeSeconds > test.getDuree() * 60) {
                    // Le temps écoulé dépasse la durée spécifiée, expirer la session
                    session.invalidate();
                    return null;
                }
            }

            // Mettre à jour les choix de l'utilisateur ici
            Set<Question> questionsParTest = test.getQuestion();
            for (Question qs : questionsParTest) {
                Set<Choix> choixParQuestion = qs.getChoix();
                for (Choix ch : choixParQuestion) {
                    for (ChoixUpdateRequest updateRequest : choixUpdates) {
                        if (ch.getId().equals(updateRequest.getId())) {
                            ch.setChoixApprenant(updateRequest.isChoixApprenant());
                            choixRepo.save(ch);
                            break;
                        }
                    }
                }
            }

            // Réinitialiser le délai d'expiration de la session à chaque mise à jour de choix
            session.setMaxInactiveInterval(test.getDuree() * 60); // Durée en secondes

            return testRepo.save(test);
        } else {
            // Gérer le cas où le test n'existe pas ou que la session a expiré
            return null;
        }
    }

    @Override
    public Test corresctionTest(Long idTest) {
        Test test=testRepo.findById(idTest).get();
        Set<Question> questionsParTest = test.getQuestion();
        for (Question qs : questionsParTest) {
            boolean allChoicesCorrect = true;
            Set<Choix> choixParQuestion = qs.getChoix();
            for (Choix ch : choixParQuestion) {
                if (ch.getChoixApprenant() != ch.isCorrection()) {
                    allChoicesCorrect = false;
                    break;
                }
            }
            if (allChoicesCorrect) {
                qs.setNoteApprenant(qs.getPointsQuestion());
            } else {
                qs.setNoteApprenant(0);
            }
            Integer noteApprenantTest = test.getNoteApprenant();
            if (noteApprenantTest == null) {
                noteApprenantTest = 0;
            }
            test.setNoteApprenant(noteApprenantTest + qs.getNoteApprenant());
        }
        return testRepo.save(test);
    }
}




