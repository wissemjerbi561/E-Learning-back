package com.example.cours.Controllers;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import com.example.cours.Services.CoursServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    @GetMapping("/courses/{id}")
    public ResponseEntity<Cours> getCourseById(@PathVariable Long id) {
        Cours course = coursService.getCourseById(id);
        if (course == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(course);
    }
    @Autowired
    CoursServiceImp coursService;

    @PostMapping("/ajoutcours")
    public Cours ajouterCr(@RequestBody Cours cr) {
        return coursService.saveCours(cr);
    }

    @GetMapping("/affichecr/{idc}")
    public Cours afficherCr(@PathVariable Long idc) {
        return coursService.retrieveCours(idc);
    }

    @GetMapping("/affichertoutCr")
    public List<Cours> affichertoutCr() {
        return coursService.retrieveAll();
    }

    @PutMapping("/modifiercr/{idc}")
    public Cours modifiercr(@PathVariable Long idc) {
        return coursService.updateCr(idc);
    }

    @DeleteMapping("/supprimercr/{idc}")
    public void supprimercr(@PathVariable Long idc) {
        coursService.delete(idc);
    }

   @PostMapping("/ajoutaffectcr")
    public Cours ajouteraffecterch(@RequestParam("imageFile") MultipartFile imageFile, @ModelAttribute Cours cr) {
        return coursService.saveAffectCr(cr, imageFile);
    }

    @GetMapping("/afficherCrParCtegorie/{nomCategorie}")
    public List<Cours> getCoursByNomCategorie(@PathVariable String nomCategorie) {
        return coursService.getCoursByNomCategorie(nomCategorie);
    }

    @GetMapping("/afficherCrParSousCtegorie/{nomSousCategorie}")
    public List<Cours> getCoursByNomSousCategorie(@PathVariable String nomSousCategorie) {
        return coursService.getCoursByNomSousCategorie(nomSousCategorie);
    }

    @GetMapping("/note-moyenne")
    public List<Cours> getCoursByNoteMoyenneSatisfaction() {
        return coursService.getCoursByNoteMoyenneSatisfaction();
    }

    @PostMapping("/ajoutaffectcrsi")
    public Cours ajouteraffecterchsi(@RequestBody Cours cr) {
        return coursService.saveAffectCrSi(cr);
    }

    @PostMapping("/updateCours")
    public ResponseEntity<Cours> updateCours(@RequestBody Cours updatedCours) {
        Cours updatedCourse = coursService.updateCours(updatedCours);

        if (updatedCourse != null) {
            return ResponseEntity.ok(updatedCourse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/deletelogicCR/{idc}")
    public Cours DELETELogic(@PathVariable Long idc) {
        return coursService.logicDeleteCours(idc);
    }
}
