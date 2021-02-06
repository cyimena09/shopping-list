package be.ifosup.magasin;

public class Magasin {
    // ATTRIBUTS
    private Integer idMagasin;
    private String nom;

    // CONSTRUCTEUR
    public Magasin(Integer idMagasin, String nom) {
        this.idMagasin = idMagasin;
        this.nom = nom;
    }

    // METHODES
    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
