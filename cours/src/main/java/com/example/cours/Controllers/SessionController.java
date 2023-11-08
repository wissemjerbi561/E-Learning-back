package com.example.cours.Controllers;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Session;
import com.example.cours.Entities.Tarif;
import com.example.cours.Services.SessionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class SessionController {
    @Autowired
    SessionServiceImp sessionService;
    @PostMapping("/ajoutaffectsession")
    public Session ajouteraffecterch(@RequestBody Session ses){
        return sessionService.saveAffectCh(ses);
    }
    @GetMapping("/affichallsession")
    public List<Session> affichertoutch(){
        return sessionService.RetrieveAllCh();
    }
    @GetMapping("/affichses/{idses}")
    public  Session afficherch(@PathVariable Long idses){
        return sessionService.RetrieveCh(idses);
    }
    @DeleteMapping("/supprimerses/{idses}")
    public void supprimertr(@PathVariable Long idses){
        sessionService.deleteCh(idses);
    }
    @PutMapping("/ajouterCoursAuSession/{idSession}/{idCours}")
        public void ajouterCourAuSession(@PathVariable Long idSession,@PathVariable Long idCours){
        sessionService.ajoutCoursSes(idSession,idCours);
    }
    @PostMapping("/updateSession")
    public ResponseEntity<Session> updateSession(@RequestBody Session updatedSession) {
        Session updatedSESSION = sessionService.updateSession(updatedSession);

        if (updatedSession != null) {
            return ResponseEntity.ok(updatedSession);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/deletelogicSession/{ids}")
    public Session DELETELogic(@PathVariable Long ids){
        return sessionService.logicDeleteSession(ids);
    }
}
