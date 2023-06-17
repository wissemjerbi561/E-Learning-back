package com.example.forum.Entities;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "publications")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;

    private String contenu;

    private String auteur;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datePublication;

    @ElementCollection
    private List<String> comments;

    @ManyToOne
    @JoinColumn(name="parent_id")
    private Publication parent;

    public void setParent(Publication parent) {
        this.parent = parent;
    }

    public Publication getParent() {
        return parent;
    }

    private int likes;


    public Publication() {
    }

    public Publication(String type, String contenu, String auteur, Date datePublication, List<String> comments, int likes) {
        this.type = type;
        this.contenu = contenu;
        this.auteur = auteur;
        this.datePublication = datePublication;
        this.comments = comments;
        this.likes = likes;
    }

    // Getters et setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }


}
