package be.ifosup.produit;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl implements ProduitDAO {
    // ATTRIBUTES
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTOR
    public ProduitDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODS
    @Override
    public List<Produit> getProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT p.idProduit, p.nom FROM produit p");

        while (resultSet.next()) {
            Long id = resultSet.getLong("idProduit");
            String nomPro = resultSet.getString("nom");

            Produit produit = new Produit(id, nomPro, null, null);
            produits.add(produit);
        }
        return produits;
    }

    @Override
    public void createProduit(Produit produit) {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO produit (nom, idCategorie, idMesure) VALUES (?, ?, ?)");

            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setLong(2, produit.getIdCategorie());
            preparedStatement.setLong(3, produit.getIdMesure());

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateProduit(Long id, Produit produit) {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE produit p SET p.nom = ? , p.idCategorie = ?, p.idMesure = ? WHERE p.idProduit = ?");

            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setLong(2, produit.getIdCategorie());
            preparedStatement.setLong(3, produit.getIdMesure());
            preparedStatement.setLong(4, produit.getIdProduit());

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteProduit(Long id) {
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM produit WHERE id = ?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
