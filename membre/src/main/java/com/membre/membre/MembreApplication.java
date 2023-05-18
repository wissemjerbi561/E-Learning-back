package com.membre.membre;

import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@EnableFeignClients
public class MembreApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        positionRepository.save(new Position(null,"Tuteur Academique"));
        positionRepository.save(new Position(null,"Apprenant"));
        positionRepository.save(new Position(null,"Tuteur Professionnel"));
    }
    @Autowired
    private PositionRepository positionRepository;

    public static void main(String[] args) {SpringApplication.run(MembreApplication.class, args);
    }

}
