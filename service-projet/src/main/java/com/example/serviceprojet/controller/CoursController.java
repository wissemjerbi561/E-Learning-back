package com.example.serviceprojet.controller;

import com.example.serviceprojet.Services.ProjetServiceImp;
import com.example.serviceprojet.entity.Cours;
import com.example.serviceprojet.repository.CoursRepository;
import com.example.serviceprojet.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/project/cours")
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
