package be.ifosup.produit;

public class Produit {
    // ATTRIBUTS
    private Integer idProduit;
    private String nom;
    private Integer idCategorie;
    private Integer idMesure;
    private String nomMesure;
    private String nomCategorie;

    // CONSTRUCTEUR
    public Produit(Integer idProduit, String nom, Integer idCategorie, Integer idMesure) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.idCategorie = idCategorie;
        this.idMesure = idMesure;
    }

    // METHODES
    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Integer idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Integer getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(Integer idMesure) {
        this.idMesure = idMesure;
    }

    public String getNomMesure() {
        return nomMesure;
    }

    public void setNomMesure(String nomMesure) {
        this.nomMesure = nomMesure;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
}
