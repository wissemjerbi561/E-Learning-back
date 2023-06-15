package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ProjetServiceImp;
import com.example.serviceprojet.repository.CoursRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/project/cours")
public class CoursController {

    @Autowired
    CoursRepository coursRepository;


    public CoursController(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }



    @GetMapping
    public ResponseEntity getAllProjets() {
        return ResponseEntity.ok(this.coursRepository.findAll());
    }
}
