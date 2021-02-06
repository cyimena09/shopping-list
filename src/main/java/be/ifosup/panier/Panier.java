package be.ifosup.panier;

public class Panier {
    // ATTRIBUTS
    private Integer idPanier;
    private Integer idMagasin;
    private Integer idProduit;
    private String nomProduit;
    private String nomMagasin;

    // CONSTRUCTEUR
    public Panier() { }

    public Panier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Panier(Integer idPanier, Integer idMagasin, Integer idProduit, String nomMagasin, String nomProduit) {
        this.idPanier = idPanier;
        this.idMagasin = idMagasin;
        this.idProduit = idProduit;
        this.nomMagasin = nomMagasin;
        this.nomProduit = nomProduit;
    }

    // METHODES
    public Integer getIdPanier() {
        return idPanier;
    }

    public void setIdPanier(Integer idPanier) {
        this.idPanier = idPanier;
    }

    public Integer getIdMagasin() {
        return idMagasin;
    }

    public void setIdMagasin(Integer idMagasin) {
        this.idMagasin = idMagasin;
    }

    public Integer getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Integer idProduit) {
        this.idProduit = idProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public String getNomMagasin() {
        return nomMagasin;
    }

    public void setNomMagasin(String nomMagasin) {
        this.nomMagasin = nomMagasin;
    }

}
