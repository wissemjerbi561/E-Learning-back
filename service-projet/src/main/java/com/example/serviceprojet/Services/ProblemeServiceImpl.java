package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Probleme;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.repository.ProblemeRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

        problemetRepository.save(probleme);

    }
    public List<Probleme> obtenirProblemesStatusFalse() {
        return problemetRepository.findByStatusFalse();
    }
   // @Override
  //  public List<Probleme> retrieveProblemesByProjet(Long idProjet) {
      //  Projet p = projetRepository.findById(idProjet).orElse(null);
     //   return problemetRepository.findByProjet(p);
   // }
   public void uploadFile(MultipartFile file) throws IOException {
       file.transferTo(new File("C:\\Users\\asus\\Desktop\\uploadfile\\" + file.getOriginalFilename()));
   }
   @Override
   public List<Probleme> getProbemeByProjet(Long idProjet) {
       Projet projet=projetRepository.findById(idProjet).orElse(null);
       return problemetRepository.findByProjet(projet);
   }


}
