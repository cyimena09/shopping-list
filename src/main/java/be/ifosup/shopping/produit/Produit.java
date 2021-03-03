package be.ifosup.shopping.produit;

import be.ifosup.shopping.categorie.Categorie;
import be.ifosup.shopping.mesure.Mesure;

public class Produit {

    // ATTRIBUTS

    private Integer idProduit;
    private String nom;
    private int idPanierProduit;
    private float quantite;
    private Mesure mesure;
    private Categorie categorie;

    // CONSTRUCTOR

    public Produit() { }

    // Création d'un produit
    public Produit(String nom, Mesure mesure, Categorie categorie) {
        this.nom = nom;
        this.mesure = mesure;
        this.categorie = categorie;
    }

    // Récupération d'un produit (avec les champs de la table intérmédiaire)
    public Produit(Integer idProduit, String nom, int idPanierProduit, float quantite, Mesure mesure, Categorie categorie) {
        this.idProduit = idProduit;
        this.nom = nom;
        this.idPanierProduit = idPanierProduit;
        this.quantite = quantite;
        this.mesure = mesure;
        this.categorie = categorie;
    }

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

    public int getIdPanierProduit() {
        return idPanierProduit;
    }

    public void setIdPanierProduit(int idPanierProduit) {
        this.idPanierProduit = idPanierProduit;
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
