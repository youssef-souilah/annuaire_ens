package com.ens.models;

public class Filiere {
    private Long id;
    private String nom;
    private Long departementId;

    // Constructors
    public Filiere() {
    }

    public Filiere(String nom, Long departementId) {
        this.nom = nom;
        this.departementId = departementId;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Long departementId) {
        this.departementId = departementId;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Filiere{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", departementId=" + departementId +
                '}';
    }
}

