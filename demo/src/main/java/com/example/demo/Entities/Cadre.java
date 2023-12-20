package com.example.demo.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name="Cadre")
public class Cadre {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    public int idCadre;
    public int CNiveau;
    public String Cadre;
    public Boolean Accessible;

    public Cadre(int idCadre, int CNiveau, String cadre, Boolean accessible) {
        this.idCadre = idCadre;
        this.CNiveau = CNiveau;
        Cadre = cadre;
        Accessible = accessible;
    }

    public Cadre() {

    }

    public void setIdCadre(int idCadre) {
        this.idCadre = idCadre;
    }

    public void setCNiveau(int CNiveau) {
        this.CNiveau = CNiveau;
    }

    public void setCadre(String cadre) {
        Cadre = cadre;
    }

    public void setAccessible(Boolean accessible) {
        Accessible = accessible;
    }

    public int getCNiveau() {
        return CNiveau;
    }

    public String getCadre() {
        return Cadre;
    }

    public Boolean getAccessible() {
        return Accessible;
    }

    @Override
    public String toString() {
        return "Cadre{" +
                "idCadre=" + idCadre +
                ", CNiveau=" + CNiveau +
                ", Cadre='" + Cadre + '\'' +
                ", Accessible=" + Accessible +
                '}';
    }
}
