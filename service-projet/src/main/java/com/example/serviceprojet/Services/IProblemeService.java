package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Probleme;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IProblemeService  {
    void ajouterEtaffecterListeProbleme(Probleme probleme, Long idProjet);
    List<Probleme> getProbemeByProjet(Long idProjet);

   // List<Probleme> retrieveProblemesByProjet(Long idProjet);
}
