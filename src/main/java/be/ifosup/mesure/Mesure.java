package be.ifosup.mesure;

public class Mesure {
    // ATTRIBUTES
    private Long idMesure;
    private String nom;

    // CONSTRUCTOR
    public Mesure(Long idMesure, String nom) {
        this.idMesure = idMesure;
        this.nom = nom;
    }

    // METHODS
    public Long getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(Long idMesure) {
        this.idMesure = idMesure;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
