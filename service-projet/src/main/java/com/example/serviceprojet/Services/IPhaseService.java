package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Phase;
import com.example.serviceprojet.entity.Probleme;

import java.util.List;

public interface IPhaseService {
    List<Phase> getPhasesByProjet(Long idProjet);

}
