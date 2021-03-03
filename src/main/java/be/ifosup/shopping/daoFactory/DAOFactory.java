package be.ifosup.shopping.daoFactory;

import be.ifosup.shopping.categorie.CategorieDAO;
import be.ifosup.shopping.categorie.CategorieDAOImpl;
import be.ifosup.shopping.magasin.MagasinDAO;
import be.ifosup.shopping.magasin.MagasinDAOImpl;
import be.ifosup.shopping.mesure.MesureDAO;
import be.ifosup.shopping.mesure.MesureDAOImpl;
import be.ifosup.shopping.panier.PanierDAO;
import be.ifosup.shopping.panier.PanierDAOImpl;
import be.ifosup.shopping.produit.ProduitDAO;
import be.ifosup.shopping.produit.ProduitDAOImpl;

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
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return new DAOFactory("jdbc:mysql://localhost:3306/shopping-list?serverTimezone=CET", "root", "");
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
