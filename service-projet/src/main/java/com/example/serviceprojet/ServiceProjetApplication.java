package com.example.serviceprojet;

import com.example.serviceprojet.entity.TypePhase;
import com.example.serviceprojet.repository.PhaseRepository;
import com.example.serviceprojet.repository.TypePhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableDiscoveryClient


public class ServiceProjetApplication implements CommandLineRunner {
	@Autowired
	private TypePhaseRepository typePhaseRepository;
	private PhaseRepository phaseRepository;


	public static void main(String[] args) {
		SpringApplication.run(ServiceProjetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Initialisation des données de type phase
		TypePhase typePhase1 = new TypePhase(1,"initilisation", 1);
		TypePhase typePhase2 = new TypePhase(2, "planning ", 2);
		TypePhase typePhase3 = new TypePhase(3, "realisation ", 3);
		TypePhase typePhase4 = new TypePhase(4, "evaluate ", 4);
		TypePhase typePhase5 = new TypePhase(5, "experience evaluation ", 5);

		// Sauvegarde des données dans la base de données
		typePhaseRepository.save(typePhase1);
		typePhaseRepository.save(typePhase2);
		typePhaseRepository.save(typePhase3);
		typePhaseRepository.save(typePhase4);
		typePhaseRepository.save(typePhase5);

	}



}
