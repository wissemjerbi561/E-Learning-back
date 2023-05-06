package com.example.cours.Services;

import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Tarif;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.TarifRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Tarif saveAffectCh(Tarif tr) {
        tarifRepo.save(tr);
        Cours cr=coursRepo.findById(tr.getIdCours()).get();
        tr.setCourstr(cr);
        return  tarifRepo.save(tr);
    }
}
