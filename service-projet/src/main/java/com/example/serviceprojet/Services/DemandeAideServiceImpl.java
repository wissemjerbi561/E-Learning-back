package com.example.serviceprojet.Services;

import com.example.serviceprojet.repository.AffectationTacheRepository;
import com.example.serviceprojet.repository.DemandeAideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DemandeAideServiceImpl implements IDemandeAideService{
    @Autowired
    private DemandeAideRepository demandeAideRepository;
}
