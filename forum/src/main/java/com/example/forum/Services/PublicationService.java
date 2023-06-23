package com.example.forum.Services;

import com.example.forum.Entities.Publication;
import com.example.forum.PublicationNotFoundException;
import com.example.forum.Repositories.PublicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationService {
    @Autowired
    private PublicationRepository publicationRepository;

    public List<Publication> getAllPublications() {
        return publicationRepository.findAll();
    }

    public Publication createPublication(Publication publication) {
        return publicationRepository.save(publication);
    }

    public Publication updatePublication(Long id, Publication publication) {
        Publication existingPublication = publicationRepository.findById(id)
                .orElse(null);
        if (existingPublication != null) {
            existingPublication.setType(publication.getType());
            existingPublication.setContenu(publication.getContenu());
            existingPublication.setAuteur(publication.getAuteur());
            existingPublication.setDatePublication(publication.getDatePublication());
            existingPublication.setComments(publication.getComments());
            existingPublication.setLikes(publication.getLikes());

            return publicationRepository.save(existingPublication);
        } else {
            return null;
        }
    }

    public void deletePublication(Long id) {
        publicationRepository.deleteById(id);
    }

    public PublicationService(PublicationRepository publicationRepository) {
        this.publicationRepository = publicationRepository;
    }

    public Publication addComment(Long publicationId, String comment) {
        Optional<Publication> optionalPublication = publicationRepository.findById(publicationId);
        if (optionalPublication.isPresent()) {
            Publication publication = optionalPublication.get();
            publication.getComments().add(comment);
            return publicationRepository.save(publication);
        } else {
            throw new PublicationNotFoundException("Publication not found with id: " + publicationId);
        }
    }
    public Publication likePublication(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId).orElse(null);
        if (publication != null) {
            publication.setLikes(publication.getLikes() + 1);
            return publicationRepository.save(publication);
        } else {
            return null;
        }
    }




    public ResponseEntity<Publication> unlikePublication( Long id) {
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



    public List<Publication> getByParent(Long parentId){
        return publicationRepository.getByParent(parentId);
    }





}
