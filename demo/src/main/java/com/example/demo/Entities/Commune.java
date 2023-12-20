package com.example.demo.Entities;

import jakarta.persistence.*;

import java.util.Date;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
@Table(name = "commune")
public class Commune {

    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Id
    public  int codeCommune;
    public  String  Libelle;
    public Date DateCreation;
    public  String Email;
    public  int  Superficie;
    public  String Adresse;
    public String Localisation;
    public boolean Accessible;
    @Column(name ="Densité")
    public int Densitee;
    public int NbLocalite;


    public Commune(int codeCommune, String libelle, Date dateCreation, String email, int superficie, String adresse, String localisation, boolean accessible, int densitee, int nbLocalite) {
        this.codeCommune = codeCommune;
        Libelle = libelle;
        DateCreation = dateCreation;
        Email = email;
        Superficie = superficie;
        Adresse = adresse;
        Localisation = localisation;
        Accessible = accessible;
        Densitee = densitee;
        NbLocalite = nbLocalite;
    }


    public Commune() {

    }

    public void setCodeCommune(int codeCommune) {
        this.codeCommune = codeCommune;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setSuperficie(int superficie) {
        Superficie = superficie;
    }

    public void setAdresse(String adresse) {
        Adresse = adresse;
    }

    public void setLocalisation(String localisation) {
        Localisation = localisation;
    }

    public void setAccessible(boolean accessible) {
        Accessible = accessible;
    }

    public void setDensitee(int densitee) {
        Densitee = densitee;
    }

    public void setNbLocalite(int nbLocalite) {
        NbLocalite = nbLocalite;
    }

    public int getCodeCommune() {
        return codeCommune;
    }

    public String getLibelle() {
        return Libelle;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public String getEmail() {
        return Email;
    }

    public int getSuperficie() {
        return Superficie;
    }

    public String getAdresse() {
        return Adresse;
    }

    public String getLocalisation() {
        return Localisation;
    }

    public boolean getAccessible() {
        return Accessible;
    }

    public int getDensitee() {
        return Densitee;
    }

    public int getNbLocalite() {
        return NbLocalite;
    }


    @Override
    public String toString() {
        return "Commune{" +
                "codeCommune=" + codeCommune +
                ", Libelle='" + Libelle + '\'' +
                ", DateCreation=" + DateCreation +
                ", Email='" + Email + '\'' +
                ", Superficie=" + Superficie +
                ", Adresse='" + Adresse + '\'' +
                ", Localisation='" + Localisation + '\'' +
                ", Accessible=" + Accessible +
                ", Densitee=" + Densitee +
                ", NbLocalite=" + NbLocalite +
                '}';
    }
}
