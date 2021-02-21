package be.ifosup.produit;

import be.ifosup.categorie.Categorie;
import be.ifosup.mesure.Mesure;

public class Produit {

    // ATTRIBUTS

    private Integer idProduit;
    private String nom;
    private float quantite;
    private Mesure mesure;
    private Categorie categorie;

    // GETTERS & SETTERS

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

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public Mesure getMesure() {
        return mesure;
    }

    public void setMesure(Mesure mesure) {
        this.mesure = mesure;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

}
