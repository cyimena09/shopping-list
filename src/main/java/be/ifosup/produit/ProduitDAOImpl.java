package be.ifosup.produit;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAOImpl implements ProduitDAO {
    // ATTRIBUTS
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTEUR
    public ProduitDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODES
    @Override
    public List<Produit> getProduits() throws SQLException {
        List<Produit> produits = new ArrayList<>();

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT p.idProduit, p.nom FROM produit p");
            // Recuperation des donnees.
            while (resultSet.next()) {
                Integer id = resultSet.getInt("idProduit");
                String nom = resultSet.getString("nom");

                Produit produit = new Produit(id, nom, null, null);
                produits.add(produit);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return produits;
    }

    @Override
    public Produit getProduitById(Integer id) throws SQLException {
        // Attribut de l'objet retourné.
        Integer idProduit = null;
        String nom = null;
        Integer idMesure = null;
        String nomMesure = null;
        Integer idCategorie = null;
        String nomCategorie = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT p.idProduit, p.nom, p.idCategorie, p.idMesure, m.nom as nomMesure " +
                    "FROM produit p " +
                    "INNER JOIN mesure m ON m.idMesure = p.idMesure " +
                    "INNER JOIN categorie c ON c.idCategorie = p.idCategorie " +
                    "WHERE p.idProduit = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            while (resultSet.next()) {
                idProduit = resultSet.getInt("idProduit");
                nom = resultSet.getString("nom");
                idMesure = resultSet.getInt("idMesure");
                nomMesure = resultSet.getString("nomMesure");
                idCategorie = resultSet.getInt("idCategorie");
                nomCategorie = resultSet.getString("nomCategorie");
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        Produit produit =  new Produit(idProduit, nom, idCategorie, idMesure);
        produit.setNomCategorie(nomCategorie);
        produit.setNomMesure(nomMesure);

        return produit;
    }

    @Override
    public void createProduit(Produit produit) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO produit (nom, idCategorie, idMesure) VALUES (?, ?, ?)");
            // Set attributes
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setInt(2, produit.getIdCategorie());
            preparedStatement.setInt(3, produit.getIdMesure());
            // Execution de la requete.
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void updateProduit(Integer id, Produit produit) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE produit p SET p.nom = ? , p.idCategorie = ?, p.idMesure = ? WHERE p.idProduit = ?");
            // Set attributes
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setInt(2, produit.getIdCategorie());
            preparedStatement.setInt(3, produit.getIdMesure());
            preparedStatement.setInt(4, id);
            // Execution de la requete.
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    @Override
    public void deleteProduit(Integer idProduit) throws SQLException {
        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM produit WHERE idProduit = ?");
            // Set attributes
            preparedStatement.setInt(1, idProduit);
            // Execution de la requete.
            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

}
