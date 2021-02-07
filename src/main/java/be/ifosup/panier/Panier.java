package be.ifosup.panier;

import be.ifosup.magasin.Magasin;
import be.ifosup.produit.Produit;

import java.util.List;

public class Panier {
    // ATTRIBUTS
    private Integer idPanier;
    private String nom;
    private Magasin magasin;
    private List<Produit> produitList;

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
