package be.ifosup.shopping.panier;

import be.ifosup.shopping.magasin.Magasin;
import be.ifosup.shopping.produit.Produit;

import java.util.List;

public class Panier {

    // ATTRIBUTS

    private Integer idPanier;
    private String nom;
    private Magasin magasin;
    private List<Produit> produitList;

    // CONSTRUCTOR

    public Panier() { }

    // Creation d'un panier (sans l'ajout de produit car on ne peut pas ajouter des produits en même temps qu'on crée un panier
    public Panier(String nom, Magasin magasin) {
        this.nom = nom;
        this.magasin = magasin;
    }

    // Récupération
    public Panier(Integer idPanier, String nom, Magasin magasin, List<Produit> produits) {
        this.idPanier = idPanier;
        this.nom = nom;
        this.magasin = magasin;
        this.produitList = produits;
    }

    // GETTERS & SETTERS

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

    public Magasin getMagasin() {
        return magasin;
    }

    public void setMagasin(Magasin magasin) {
        this.magasin = magasin;
    }

    public List<Produit> getProduitList() {
        return produitList;
    }

    public void setProduitList(List<Produit> produitList) {
        this.produitList = produitList;
    }

}
