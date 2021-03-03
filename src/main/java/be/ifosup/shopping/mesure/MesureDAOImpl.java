package be.ifosup.shopping.mesure;

import be.ifosup.shopping.daoFactory.DAOFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesureDAOImpl implements MesureDAO {

    // ATTRIBUTS

    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTEUR

    public MesureDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODES

    @Override
    public List<Mesure> getMesures() throws SQLException {
        List<Mesure> mesures = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT m.idMesure, m.nom FROM mesure m");

            while (resultSet.next()) {
                Integer idMesure = resultSet.getInt("idMesure");
                String nom = resultSet.getString("nom");

                Mesure mesure = new Mesure(idMesure, nom);
                mesures.add(mesure);
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

        return mesures;
    }

    @Override
    public Mesure getMesureById(Integer id) throws SQLException {
        // Attribut de l'objet retourné.
        Integer idMesure = null;
        String nom = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement("SELECT m.idMesure, m.nom FROM mesure m WHERE m.idMesure = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            if (resultSet.next()) {
                idMesure = resultSet.getInt("idMesure");
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

         return new Mesure(idMesure, nom);
    }

    @Override
    public void createMesure(Mesure mesure) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO mesure (nom) VALUES (?)");

            preparedStatement.setString(1, mesure.getNom());

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
    public void updateMesure(Integer id, Mesure mesure) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE mesure m SET m.nom = ? WHERE m.idMesure = ?");

            preparedStatement.setString(1, mesure.getNom());
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
    public void deleteMesure(Integer idMesure) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM mesure WHERE idMesure = ?");
            // Set attributes
            preparedStatement.setInt(1, idMesure);
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
