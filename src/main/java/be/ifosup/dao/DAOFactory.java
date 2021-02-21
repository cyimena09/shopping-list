package be.ifosup.dao;

import be.ifosup.categorie.CategorieDAO;
import be.ifosup.categorie.CategorieDAOImpl;
import be.ifosup.magasin.MagasinDAO;
import be.ifosup.magasin.MagasinDAOImpl;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.mesure.MesureDAOImpl;
import be.ifosup.panier.PanierDAO;
import be.ifosup.panier.PanierDAOImpl;
import be.ifosup.produit.ProduitDAO;
import be.ifosup.produit.ProduitDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

    // ATTRIBUTS

    private final String url;
    private final String userName;
    private final String password;

    // CONSTRUCTEUR

    public DAOFactory(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    // METHODES

    // Chargement du driver
    public static DAOFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/shopping-list?serverTimezone=CET", "admin", "admin");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    // Récupération des DAO par tables SQL
    public ProduitDAO getProduitDAO() {
        return new ProduitDAOImpl(this);
    }

    public MesureDAO getMesureDAO() {
        return new MesureDAOImpl(this);
    }

    public PanierDAO getPanierDAO() {
        return new PanierDAOImpl(this);
    }

    public MagasinDAO getMagasinDAO() {
        return new MagasinDAOImpl(this);
    }

    public CategorieDAO getCategorieDAO() {
        return new CategorieDAOImpl(this);
    }

}
