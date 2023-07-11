package com.example.serviceprojet.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;

import java.io.Serializable;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "type")
public class Type implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long typeId;

    private String typeName;

    private String path;
    private String code;
    public void saveOrUpdate() {
        try {
            // Code pour effectuer les opérations de sauvegarde ou de mise à jour

            // Exemple : entityManager.persist(this);
        } catch (DataIntegrityViolationException ex) {
            // Récupérer les détails de l'exception
            String errorMessage = ex.getMessage();
            // Vous pouvez également accéder à la cause racine de l'exception si nécessaire
            Throwable rootCause = ex.getRootCause();

            // Traitez l'erreur ou enregistrez les informations dans les journaux
            // ...
        }
    }
}
