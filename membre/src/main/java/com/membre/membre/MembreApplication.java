package com.membre.membre;

import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@EnableFeignClients
public class MembreApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        positionRepository.save(new Position(null,"Tuteur Academique",1));
        positionRepository.save(new Position(null,"Apprenant", 2));
        positionRepository.save(new Position(null,"Tuteur Professionnel",3));
        //positionRepository.save(new Position(null,"Apprenant Aide",4));
        //positionRepository.save(new Position(null,"Apprenant Realisation",5));
        //positionRepository.save(new Position(null,"Apprenant verification",6));
    }
    @Autowired
    private PositionRepository positionRepository;

    public static void main(String[] args) {SpringApplication.run(MembreApplication.class, args);
    }



}
