package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemeServiceImpl  implements IProblemeService{
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    ProblemeRepository problemetRepository;

    @Override
    public void ajouterEtaffecterListeProbleme(Probleme probleme, Long idProjet){
        // TODO Auto-generated method stub

        Projet projet = projetRepository.findById(idProjet).orElse(null);
       // User User = userRepository.findById(Id).orElse(null);
        probleme.setProjet(projet);
      //  probleme.setUser(User);
        problemetRepository.save(probleme);

    }
   // @Override
  //  public List<Probleme> retrieveProblemesByProjet(Long idProjet) {
      //  Projet p = projetRepository.findById(idProjet).orElse(null);
     //   return problemetRepository.findByProjet(p);
   // }
   @Override
   public List<Probleme> getProbemeByProjet(Long idProjet) {
       Projet projet=projetRepository.findById(idProjet).orElse(null);
       return problemetRepository.findByProjet(projet);
   }


}
