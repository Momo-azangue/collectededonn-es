package com.example.demo.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Pays {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int CodePays;
    public String Libelle;
    public Boolean Accessible;
    public String Densite;
    public int Superficie;
    public Date DateIndependance;
    public Date DateReunification;
    public Date DateUnification;

    public Pays() {

    }

    public void setSuperficie(int superficie) {
        Superficie = superficie;
    }

    @Override
    public String toString() {
        return "Pays{" +
                "CodePays=" + CodePays +
                ", Libelle='" + Libelle + '\'' +
                ", Accessible=" + Accessible +
                ", Densite='" + Densite + '\'' +
                ", Superficie=" + Superficie +
                ", DateIndependance=" + DateIndependance +
                ", DateReunification=" + DateReunification +
                ", DateUnification=" + DateUnification +
                '}';
    }

    public void setCodePays(int codePays) {
        CodePays = codePays;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public Boolean getAccessible() {
        return Accessible;
    }

    public void setAccessible(Boolean accessible) {
        Accessible = accessible;
    }

    public String getDensite() {
        return Densite;
    }

    public void setDensite(String densite) {
        Densite = densite;
    }

    public int getSuperficie() {
        return Superficie;
    }

    public Date getDateIndependance() {
        return DateIndependance;
    }

    public void setDateIndependance(Date dateIndependance) {
        DateIndependance = dateIndependance;
    }

    public Date getDateReunification() {
        return DateReunification;
    }

    public void setDateReunification(Date dateReunification) {
        DateReunification = dateReunification;
    }

    public Date getDateUnification() {
        return DateUnification;
    }

    public void setDateUnification(Date dateUnification) {
        DateUnification = dateUnification;
    }

    public Pays(int codePays, String libelle, Boolean accessible, String densite, int superficie, Date dateIndependance, Date dateReunification, Date dateUnification) {
        CodePays = codePays;
        Libelle = libelle;
        Accessible = accessible;
        Densite = densite;
        Superficie = superficie;
        DateIndependance = dateIndependance;
        DateReunification = dateReunification;
        DateUnification = dateUnification;
    }
}
