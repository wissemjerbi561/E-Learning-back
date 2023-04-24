package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Cours;
import com.example.serviceprojet.entity.Projet;
import com.example.serviceprojet.entity.Tache;
import com.example.serviceprojet.repository.ProjetRepository;
import com.example.serviceprojet.repository.TacheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TacheServiceImpl implements  ITacheService{
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    TacheRepository tacheRepository;




}
