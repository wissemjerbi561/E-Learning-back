package com.example.cours.Services;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Lecon;

import java.util.List;

public interface LeconService {
    public Lecon saveAffectLecon(Lecon l);
    public Lecon retrieveLecon(Long idl);
    public List<Lecon> retrieveAll();

    public void delete(Long idl);
    public Lecon updateLecon(Lecon updatedLecon);
}
