package be.ifosup.categorie;

public class Categorie {
    // ATTRIBUTS
    private Integer idCategorie;
    private String nom;

    // CONSTRUCTEUR

    public Categorie() { }

    public Categorie(Integer idCategorie, String nom) {
        this.idCategorie = idCategorie;
        this.nom = nom;
    }

    // METHODES
    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
