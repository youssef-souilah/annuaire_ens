package com.ens.models;

public class Departement {
    private int id;
    private String nom;

    // Constructors
    public Departement() {
    }

    public Departement(String nom) {
        this.nom = nom;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // toString method for debugging
    @Override
    public String toString() {
        return "Departement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
