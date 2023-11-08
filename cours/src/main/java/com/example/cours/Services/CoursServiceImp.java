package com.example.cours.Services;



import com.example.cours.Entities.*;
import com.example.cours.FileUtil;
import com.example.cours.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.web.multipart.MultipartFile;
import org.springframework.util.StringUtils; // Pour utiliser des méthodes utilitaires sur les chaînes de caractères

import java.io.IOException; // Pour gérer les exceptions liées aux opérations d'entrée/sortie


import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CoursServiceImp implements CoursService{

    public Cours getCourseById(Long idc) {
        return coursRepo.findById(idc).orElse(null);
    }
    @Autowired
    CoursRepo coursRepo;
    @Autowired
    AviCoursRepo aviCoursRepo;
    @Autowired
    ThemeRepo themeRepo;
    @Autowired
    CategorieRepo categorieRepo;
    @Autowired
    SousCategorieRepo sousCategorieRepo;

    @Override
    public Cours saveCours(Cours c) {
        return coursRepo.save(c);
    }

    @Override
    public Cours retrieveCours(Long idCr) {
        return coursRepo.findById(idCr).get();
    }

    @Override
    public List<Cours> retrieveAll() {
       // List<Cours> listcr=( List<Cours>)coursRepo.findAll();
        return coursRepo.findCoursInactifs();
    }

    @Override
    public Cours updateCr(Long idc) {
        Cours cr= coursRepo.findById(idc).get();
        return  coursRepo.save(cr);
    }

    @Override
    public void delete(Long idc) {
        Cours cr= coursRepo.findById(idc).get();
        coursRepo.delete(cr);

    }

    @Override
    public void calculNotemoyenne(Long idcr) {
        Cours cr = coursRepo.findById(idcr).orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + idcr));

        List<AviCours> listavis = (List<AviCours>) aviCoursRepo.findAll();
        List<AviCours> listaviCours = new ArrayList<>();
        for (AviCours monAvis : listavis) {
            if (monAvis.getCoursav() != null && monAvis.getCoursav().equals(cr)) {
                listaviCours.add(monAvis);
            }
        }
        int dividande = listaviCours.size();
        if (dividande > 0) {
            int noteTotale = 0;
            for (AviCours monAvis : listaviCours) {
                noteTotale += monAvis.getNoteSatisfaction();
            }
            float noteSatisfaction = (float) noteTotale / dividande;
            cr.setNoteMoyenneSatisfaction(noteSatisfaction);
        } else {
            cr.setNoteMoyenneSatisfaction(0); // or any default value when no avis are available
        }
        coursRepo.save(cr);
    }

    @Override
    public Cours saveAffectCr(Cours cr, MultipartFile imageFile) {
        String fileName = UUID.randomUUID().toString(); // Générer un nom de fichier unique
        String fileExtension = StringUtils.getFilenameExtension(imageFile.getOriginalFilename());
        String savedFileName = fileName + "." + fileExtension;
        String uploadDir = "/path/to/upload/directory"; // Remplacez par le répertoire de téléchargement réel sur votre système
        String filePath = uploadDir + "/" + savedFileName;

        try {
            Files.copy(imageFile.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            // Gérer l'exception en conséquence
            e.printStackTrace();
        }

        // Mettre à jour les propriétés du cours
        cr.setImageUrl(savedFileName);

        // Sauvegarder le cours avec les modifications

        coursRepo.save(cr);
        Theme th = themeRepo.findById(cr.getIdTheme()).orElse(null);
        cr.setTheme(th);
        Categorie cat=categorieRepo.findById(cr.getIdCategorie()).get();
        cr.setCategorie(cat);
        SousCategorie souscat=sousCategorieRepo.findById(cr.getIdSousCategorie()).get();
        cr.setSousCategorie(souscat);

        return  coursRepo.save(cr);
    }

    @Override
    public Cours saveAffectCrSi(Cours cr) {
        coursRepo.save(cr);
        Theme th = themeRepo.findById(cr.getIdTheme()).orElse(null);
        cr.setTheme(th);

        if(cr.getIdCategorie()!=null) {

            Categorie cat = categorieRepo.findById(cr.getIdCategorie()).get();
            cr.setCategorie(cat);
        }
        if(cr.getIdSousCategorie()!=null) {
            SousCategorie souscat = sousCategorieRepo.findById(cr.getIdSousCategorie()).get();
            cr.setSousCategorie(souscat);
        }

        return  coursRepo.save(cr);
    }

    public List<Cours> getCoursByNomCategorie(String nomCategorie) {
        return coursRepo.getCoursByNomCategorie(nomCategorie);
    }
    public List<Cours> getCoursByNomSousCategorie(String nomSousCategorie) {
        return coursRepo.getCoursByNomSousCategorie(nomSousCategorie);
    }
    public List<Cours> getCoursByNoteMoyenneSatisfaction() {
        return coursRepo.getCoursByNoteMoyenneSatisfaction();
    }
    public Cours updateCours(Cours updatedCours) {
        if (updatedCours.getIdCours() != null && coursRepo.existsById(updatedCours.getIdCours())) {
            return coursRepo.save(updatedCours);
        } else {
            return null;
        }
    }

    @Override
    public Cours logicDeleteCours(Long idcr) {
        Cours cours=coursRepo.findById(idcr).get();
        cours.setActif(true);
        return coursRepo.save(cours);
    }
}
