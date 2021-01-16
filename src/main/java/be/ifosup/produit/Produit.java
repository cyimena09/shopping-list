package be.ifosup.produit;

public class Produit {
    // ATTRIBUTES
    private Long idProduit;
    private String nom;
    private Long idCategorie;
    private Long idMesure;

    public Produit(Long idProduit, String nom, Long idCategorie, Long idMesure) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.idCategorie = idCategorie;
        this.idMesure = idMesure;
    }

    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idProduit) {
        this.idProduit = idProduit;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(Long idCategorie) {
        this.idCategorie = idCategorie;
    }

    public Long getIdMesure() {
        return idMesure;
    }

    public void setIdMesure(Long idMesure) {
        this.idMesure = idMesure;
    }
}
