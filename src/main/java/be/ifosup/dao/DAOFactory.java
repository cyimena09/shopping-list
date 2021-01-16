package be.ifosup.dao;

import be.ifosup.produit.ProduitDAO;
import be.ifosup.produit.ProduitDAOImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    // ATTRIBUTES
    private final String url;
    private final String userName;
    private final String password;

    // CONSTRUCTOR
    public DAOFactory(String url, String userName, String password){
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    // METHODS
    // Load driver
    public static DAOFactory getInstance(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        DAOFactory instance = new DAOFactory("jdbc:mysql://localhost:3306/shopping-list?serverTimezone=CET", "root", "");
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }

    // Get Dao for sql tables
    public ProduitDAO getProduitDAO() { return new ProduitDAOImpl(this); }


    // décommenter lorsque les classes auront été créé
//    public MagasinDAO getMagasinDAO(){
//        return new MagasinDAOImpl(this);
//    }
//
//    public PanierDAO getPanierDAO(){
//        return new PanierDAOImpl(this);
//    }
//
//
//    public CategorieDAO getCategorieDAO(){
//        return new CategorieImpl(this);
//    }
//
//    public MesureDAO getMesureDAO() { return new MesureDAOImpl(this); }
}
