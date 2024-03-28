package com.ens.models;

public class Etudiant {
    private Long id;
    private Long CNE;
    private String nom;
    private String prenom;
    private String filiere;
    private String departement;
    private String telephone;

    // Constructors
    public Etudiant() {
    }

    public Etudiant(Long CNE, String nom, String prenom, String filiere, String departement, String telephone) {
        this.CNE = CNE;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.departement = departement;
        this.telephone = telephone;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCNE() {
        return CNE;
    }

    public void setCNE(Long CNE) {
        this.CNE = CNE;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Etudiant{" +
                "id=" + id +
                ", CNE=" + CNE +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", filiere='" + filiere + '\'' +
                ", departement='" + departement + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}

