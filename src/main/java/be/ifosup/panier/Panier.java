package be.ifosup.panier;

public class Panier {
    // ATTRIBUTS
    private Integer idPanier;
    private String nom;

    // CONSTRUCTEUR
    public Panier(Integer idPanier, String nom) {
        this.idPanier = idPanier;
        this.nom = nom;
    }

    // METHODES
    public Integer getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
