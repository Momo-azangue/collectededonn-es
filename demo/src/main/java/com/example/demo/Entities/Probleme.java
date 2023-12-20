package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Probleme {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idProbleme;
    public String Probleme;
    public Boolean Archive;

    public Probleme(int idProbleme, String probleme, Boolean archive) {
        this.idProbleme = idProbleme;
        Probleme = probleme;
        Archive = archive;
    }

    public Probleme() {

    }

    @Override
    public String toString() {
        return "Probleme{" +
                "idProbleme=" + idProbleme +
                ", Probleme='" + Probleme + '\'' +
                ", Archive=" + Archive +
                '}';
    }

    public void setIdProbleme(int idProbleme) {
        this.idProbleme = idProbleme;
    }

    public String getProbleme() {
        return Probleme;
    }

    public void setProbleme(String probleme) {
        Probleme = probleme;
    }

    public Boolean getArchive() {
        return Archive;
    }

    public void setArchive(Boolean archive) {
        Archive = archive;
    }
}
