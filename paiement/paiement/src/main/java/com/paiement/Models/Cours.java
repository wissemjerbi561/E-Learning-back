package com.paiement.Models;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Cours {
    private Long coursId;
    private String Description;
    private int nbrOfInscription ;
    private int nbrOfSucceeded;
    private int nbrOfChapters;
    private Date startDate;
    private List<String> chapters;
    private int nbrAbsenceByApp;
    private int satisfactionRating;
    private float averageSatisfactionRating;
}