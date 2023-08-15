package com.example.serviceprojet.repository;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "microservice-cours")

public interface CoursServiceProxy {

    @PostMapping("/api/cours/{coursId}/associer-projet/{projetId}")
    void associerCoursAProjet(@PathVariable("projetId") Long projetId, @PathVariable("coursId") Long coursId);

}

