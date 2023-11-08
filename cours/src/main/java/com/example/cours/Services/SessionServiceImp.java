package com.example.cours.Services;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Session;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@Service
public class SessionServiceImp implements SessionService{
    @Autowired
    SessionRepo sessionRepo;
    @Autowired
    CoursRepo coursRepo;

    @Override
    public Session RetrieveCh(Long idses) {
        return sessionRepo.findById(idses).get();
    }

    @Override
    public List<Session> RetrieveAllCh() {
       // List<Session> session =( List<Session>)sessionRepo.findAll();
       // return session;
        return sessionRepo.findInactiveSessions();
    }

    @Override
    public void deleteCh(Long idses) {
sessionRepo.deleteById(idses);
    }

    @Override
    public Session saveAffectCh(Session ses) {

        sessionRepo.save(ses);


        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                Date now = new Date();
                 if (now.after(ses.getDateFin())) {
                    ses.setActive(false);
                } else {
                    ses.setActive(true);
                }

                // Enregistrement de la session mise à jour dans la base de données
                sessionRepo.save(ses);
            }
        }, ses.getDateDebut());

        return sessionRepo.save(ses);
    }

    @Override
    public void ajoutCoursSes(Long idses, Long idcr) {
        Session ses=sessionRepo.findById(idses).get();
        Cours cr=coursRepo.findById(idcr).get();
        ses.getCourss().add(cr);
        sessionRepo.save(ses);

    }

    public Session updateSession(Session updatedSession) {
        if (updatedSession.getIdSession() != null && coursRepo.existsById(updatedSession.getIdSession())) {
            return sessionRepo.save(updatedSession);
        } else {
            return null;
        }
    }

    @Override
    public Session logicDeleteSession(Long idSesson) {
        Session session=sessionRepo.findById(idSesson).get();
        session.setActif(true);
        return sessionRepo.save(session);
    }
}
