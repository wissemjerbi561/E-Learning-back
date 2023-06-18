package com.example.cours.Services;

import com.example.cours.Entities.Chapitre;
import com.example.cours.Entities.Cours;
import com.example.cours.Entities.Seance;
import com.example.cours.Entities.Session;
import com.example.cours.Repositories.CoursRepo;
import com.example.cours.Repositories.SeanceRepo;
import com.example.cours.Repositories.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class SeanceServiceImp implements SeanceService{
    @Autowired
    SeanceRepo seanceRepo;
    @Autowired
    CoursRepo coursRepo;
    @Autowired
    SessionRepo sessionRepo;
    @Override
    public Seance savese(Seance s) {
        return seanceRepo.save(s);
    }

    @Override
    public Seance RetrieveSe(Long idse) {
        return seanceRepo.findById(idse).get();
    }

    @Override
    public List<Seance> RetrieveAllse() {
        List<Seance> seances=(List<Seance>)seanceRepo.findAll();
        return seances;
    }

   /* @Override
    public void Affectse(Long ids, Long idses) {
Seance s =seanceRepo.findById(ids).get();
        Cours cr=coursRepo.findById(idcr).get();
        s.setCourssc(cr);
        seanceRepo.save(s);
    }*/

    @Override
    public void deleteSc(Long ids) {
        Seance s =seanceRepo.findById(ids).get();
        seanceRepo.delete(s);
    }

    /* @Override
     public Seance saveAffectSc(Seance sc) {
         seanceRepo.save(sc);
         Session ses = sessionRepo.findById(sc.getIdSession()).orElse(null);
         if (ses != null) {
             sc.setSessionsc(ses);

             // Générer le lien Google Meet basé sur la date de début de la séance
             LocalDate dateDebut = sc.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
             LocalTime heureReunion = sc.getHeureReunion().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
             String googleMeetLink = generateGoogleMeetLinkFromDateAndTime(dateDebut,heureReunion);

             // Associer le lien Google Meet à la séance
             sc.setLienGoogleMeet(googleMeetLink);

             // Enregistrer la séance mise à jour
             return seanceRepo.save(sc);
         }
         return null;
     }
     private String generateGoogleMeetLinkFromDateAndTime(LocalDate date, LocalTime time) {
         // Convertir la date en un format spécifique pour extraire les informations nécessaires
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
         String formattedDate = dateFormat.format(date);

         // Convertir l'heure en un format spécifique pour extraire les informations nécessaires
         SimpleDateFormat timeFormat = new SimpleDateFormat("HHmm");
         String formattedTime = timeFormat.format(time);

         // Générer le code de réunion basé sur la date et l'heure
         String meetingCode = "meeting-" + formattedDate + "-" + formattedTime; // Exemple : meeting-20230517-1430

         // Construire le lien Google Meet avec le code de réunion
         String googleMeetLink = "https://meet.google.com/" + meetingCode;

         return googleMeetLink;
     }*/
    @Override
    public Seance saveAffectSc(Seance sc) {
        seanceRepo.save(sc);
        Session ses = sessionRepo.findById(sc.getIdSession()).orElse(null);
        if (ses != null) {
            sc.setSessionsc(ses);

            // Générer le lien Google Meet basé sur la date de début de la séance
            LocalDate dateDebut = sc.getDateDebut().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime heureReunion = sc.getHeureReunion().toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
            String googleMeetLink = generateBigBlueButtonLinkFromDateAndTime(dateDebut, heureReunion);

            // Associer le lien Google Meet à la séance
            ////////////////
            // sc.setLienGoogleMeet(googleMeetLink);

            // Enregistrer la séance mise à jour
            return seanceRepo.save(sc);
        }
        return null;
    }


    ///////////////////////////////////
   /* private String generateGoogleMeetLinkFromDateAndTime(LocalDate date, LocalTime time) {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(dateFormatter);


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedTime = time.format(timeFormatter);

        // Générer le code de réunion basé sur la date et l'heure
       // String meetingCode = formattedDate + "-" + formattedTime;
        String meetingCode = UUID.randomUUID().toString().substring(0, 8);
        // Construire le lien Google Meet avec le code de réunion

        try {
            // Encode the meeting name with URL encoding
            String meetingName = URLEncoder.encode("Meeting " + formattedDate + " " + formattedTime, StandardCharsets.UTF_8.toString());

            // Construct the Google Meet link with the encoded meeting name
            String googleMeetLink = "https://meet.google.com/new?hs=122&authuser=0#video-call-space/" + meetingName;

            return googleMeetLink;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }*/
    private String generateBigBlueButtonLinkFromDateAndTime(LocalDate date, LocalTime time) {
        // Convertir la date en un format spécifique pour extraire les informations nécessaires
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String formattedDate = date.format(dateFormatter);


        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedTime = time.format(timeFormatter);

        // Générer le code de réunion basé sur la date et l'heure
        // String meetingCode = formattedDate + "-" + formattedTime;
        String meetingCode = UUID.randomUUID().toString().substring(0, 8);
        //  String meetingName = URLEncoder.encode("Meeting " + formattedDate + " " + formattedTime, StandardCharsets.UTF_8.toString());
        // Construire le lien BigBlueButton avec le code de réunion
        String bigBlueButtonLink = "https://biggerbluebutton.com/rooms/" + meetingCode;

        return bigBlueButtonLink;
    }
}
