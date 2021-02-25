package be.ifosup.categorie;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAOImpl implements CategorieDAO {

    // ATTRIBUTS

    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTEUR

    public CategorieDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODES

    @Override
    public List<Categorie> getCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT ca.idCategorie, ca.nom FROM categorie ca");

            while (resultSet.next()) {
                // On récupère les données
                Integer idCategorie = resultSet.getInt("idCategorie");
                String nom = resultSet.getString("nom");

                Categorie categorie = new Categorie(idCategorie, nom);
                categories.add(categorie);
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

        return categories;
    }

    @Override
    public Categorie getCategorieById(Integer id) throws SQLException {
        // Attribut de l'objet retourné.
        Integer idCategorie = null;
        String nom = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT ca.idCategorie, ca.nom FROM categorie ca WHERE ca.idCategorie = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            if (resultSet.next()) {
                idCategorie = resultSet.getInt("idCategorie");
                nom = resultSet.getString("nom");
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

        return new Categorie(idCategorie, nom);
    }

    // Récupère toutes les catégories pour lesquels il existe un produit
    @Override
    public List<Categorie> getCategoriesIfProductExist() throws SQLException {
        List<Categorie> categories = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT DISTINCT ca.idCategorie, ca.nom FROM categorie ca " +
                                                        "INNER JOIN produit p ON p.idCategorie = ca.idCategorie");

            while (resultSet.next()) {
                // On récupère les données
                Integer idCategorie = resultSet.getInt("idCategorie");
                String nom = resultSet.getString("nom");

                Categorie categorie = new Categorie(idCategorie, nom);
                categories.add(categorie);
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

        return categories;
    }


    @Override
    public void createCategorie(Categorie categorie) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO categorie (nom) VALUES (?)");

            preparedStatement.setString(1, categorie.getNom());

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
    public void updateCategorie(Integer id, Categorie categorie) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE categorie m SET m.nom = ? WHERE m.idCategorie = ?");

            preparedStatement.setString(1, categorie.getNom());
            preparedStatement.setInt(2, id);

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
    public void deleteCategorie(Integer idCategorie) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM categorie WHERE idCategorie = ?");
            // Set attributes
            preparedStatement.setInt(1, idCategorie);
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
