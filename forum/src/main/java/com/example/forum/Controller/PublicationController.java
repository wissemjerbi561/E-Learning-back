package com.example.forum.Controller;

import com.example.forum.Entities.Publication;
import com.example.forum.Repositories.PublicationRepository;
import com.example.forum.Services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin ("*")
@RestController
@RequestMapping("/api/forum/publications")
public class PublicationController {
    @Autowired
    private PublicationRepository publicationRepository;
    @Autowired
    private PublicationService publicationService;


    @GetMapping
    public ResponseEntity<List<Publication>> getAllPublications() {
        List<Publication> publications = publicationRepository.findAll();
        return new ResponseEntity<>(publications, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Publication> createPublication(@RequestBody Publication publication) {
        Publication createdPublication = publicationRepository.save(publication);
        return new ResponseEntity<>(createdPublication, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@PathVariable Long id, @RequestBody Publication publication) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElse(null);
        if (existingPublication != null) {
            existingPublication.setType(publication.getType());
            existingPublication.setContenu(publication.getContenu());
            existingPublication.setAuteur(publication.getAuteur());
            existingPublication.setDatePublication(publication.getDatePublication());
            existingPublication.setComments(publication.getComments());
            existingPublication.setLikes(publication.getLikes());

            Publication updatedPublication = publicationRepository.save(existingPublication);
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePublication(@PathVariable Long id) {
        publicationRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<Publication> addComment(@PathVariable Long id, @RequestBody String comment) {
        Publication publication = publicationRepository.findById(id)
                .orElse(null);
        if (publication != null) {
            publication.getComments().add(comment);

            Publication updatedPublication = publicationRepository.save(publication);
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/{id}/like")
    public ResponseEntity<Publication> likePublication(@PathVariable Long id) {
        Publication publication = publicationRepository.findById(id).orElse(null);
        if (publication != null) {
            publication.setLikes(publication.getLikes() + 1);
            Publication updatedPublication = publicationRepository.save(publication);
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





    @PostMapping("/{id}/unlike")
    public ResponseEntity<Publication> unlikePublication(@PathVariable Long id) {
        Publication publication = publicationRepository.findById(id).orElse(null);
        if (publication != null) {
            if (publication.getLikes() > 0) {
                publication.setLikes(publication.getLikes() - 1);
            }
            Publication updatedPublication = publicationRepository.save(publication);
            return new ResponseEntity<>(updatedPublication, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




    @GetMapping("/byParent/{parentId}")

    public List<Publication> getByParent(@PathVariable Long parentId){
        return publicationRepository.getByParent(parentId);
    }

    @GetMapping("/publication")

    public List<Publication> getPublications(){
        return publicationRepository.getPublications();
    }





}




