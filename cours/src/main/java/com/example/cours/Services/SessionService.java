package com.example.cours.Services;

import com.example.cours.Entities.Session;
import com.example.cours.Entities.Tarif;

import java.util.List;

public interface SessionService {
    public Session RetrieveCh(Long idses);
    public List<Session> RetrieveAllCh();

    public void deleteCh(Long idses);
    public Session saveAffectCh(Session ses);
    public void ajoutCoursSes(Long idses,Long idcr);
}
