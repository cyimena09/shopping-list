package be.ifosup.panier;

import be.ifosup.dao.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanierDAOImpl implements PanierDAO {
    // ATTRIBUTS
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTEUR
    public PanierDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODES
    @Override
    public List<Panier> getPaniers() throws SQLException {
        List<Panier> paniers = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT m.idPanier, m.nom FROM panier m");

            while (resultSet.next()) {
                Integer idPanier = resultSet.getInt("idPanier");
                String nom = resultSet.getString("nom");

                Panier panier = new Panier(idPanier, nom);
                paniers.add(panier);
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

        return paniers;
    }

    @Override
    public Panier getPanierById(Integer id) throws SQLException {
        // Attribut de l'objet retourné.
        Integer idPanier = null;
        String nom = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT m.idPanier, m.nom FROM panier m WHERE m.idPanier = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            while (resultSet.next()) {
                idPanier = resultSet.getInt("idPanier");
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

        return new Panier(idPanier, nom);
    }

    @Override
    public void createPanier(Panier panier) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO panier (nom) VALUES (?)");

            preparedStatement.setString(1, panier.getNom());

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
    public void updatePanier(Integer id, Panier panier) throws SQLException {
        System.out.println(id);
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE panier m SET m.nom = ? WHERE m.idPanier = ?");

            preparedStatement.setString(1, panier.getNom());
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
    public void deletePanier(Integer idPanier) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM panier WHERE idPanier = ?");
            // Set attributes
            preparedStatement.setInt(1, idPanier);
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
