package com.example.cours.Services;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Tarif;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.TarifRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TarifServiceImp implements TarifService{
    @Autowired
    TarifRepo tarifRepo;
    @Autowired
    CoursRepo coursRepo;

    @Override
    public Tarif RetrieveCh(Long idtr) {
        return tarifRepo.findById(idtr).get();
    }

    @Override
    public List<Tarif> RetrieveAllCh() {
        List<Tarif> tarifs=(List<Tarif>)tarifRepo.findAll();
        return tarifs;
    }

    @Override
    public void deleteCh(Long idtr) {
Tarif tr=tarifRepo.findById(idtr).get();
tarifRepo.delete(tr);
    }

    @Override
   /* public Tarif saveAffectCh(Tarif tr) {
        tarifRepo.save(tr);
        Cours cr=coursRepo.findById(tr.getIdCours()).get();
        tr.setCourstr(cr);
        return  tarifRepo.save(tr);
    }*/
    public Tarif saveAffectCh(Tarif tr) {
        List<Tarif> Tarifexistants = (List<Tarif>) tarifRepo.findAll();
        Cours cr = coursRepo.findById(tr.getIdCours()).orElse(null);
        if (cr == null) {
            throw new IllegalArgumentException("Le cours avec l'id " + tr.getIdCours() + " n'existe pas");
        }
        tr.setCourstr(cr);

        for (Tarif tarifexistant : Tarifexistants) {
            if (tarifexistant.getCourstr() == cr && tarifexistant.getDateExpiration() == null) {
                tarifexistant.setDateExpiration(tr.getDateDebut());
                tarifRepo.save(tarifexistant);
            } else if (tarifexistant.getCourstr() == cr && tarifexistant.getDateExpiration() != null && tarifexistant.getDateExpiration().after(new Date())) {
                tarifexistant.setDateExpiration(tr.getDateDebut());
                tarifRepo.save(tarifexistant);
            }
        }
        return tarifRepo.save(tr);
    }
    public Tarif getTarifCourantParCours(Long id) {
        return tarifRepo.findByCoursIdAndDateExpirationNull(id);
    }
}
