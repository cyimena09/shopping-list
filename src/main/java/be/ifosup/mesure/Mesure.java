package be.ifosup.mesure;

public class Mesure {
    // ATTRIBUTS
    private Integer idMesure;
    private String nom;

    // CONSTRUCTEUR

    public Mesure() {}

    public Mesure(Integer idMesure, String nom) {
        this.idMesure = idMesure;
        this.nom = nom;
    }

    // METHODES
    public Integer getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(Integer idMesure) {
        this.idMesure = idMesure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
