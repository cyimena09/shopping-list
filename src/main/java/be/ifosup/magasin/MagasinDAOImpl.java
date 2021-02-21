package be.ifosup.magasin;

import be.ifosup.dao.DAOFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MagasinDAOImpl implements MagasinDAO {

    // Outils pour débuger.
//    private static final Logger LOGGER = LoggerFactory.getLogger(MagasinDAOImpl.class);

    // ATTRIBUTS

    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTEUR

    public MagasinDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODES

    @Override
    public List<Magasin> getMagasins() throws SQLException {
        List<Magasin> magasins = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT m.idMagasin, m.nom AS nomMagasin FROM magasin m");

            while (resultSet.next()) {
                Integer idMagasin = resultSet.getInt("idMagasin");
                String nomMagasin = resultSet.getString("nomMagasin");
                // on set les valeurs dans magasin
                Magasin magasin = new Magasin();
                magasin.setIdMagasin(idMagasin);
                magasin.setNom(nomMagasin);
                // On ajoute le magasin dans la liste des magasins
                magasins.add(magasin);
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

        return magasins;
    }

    @Override
    public Magasin getMagasinById(Integer id) throws SQLException {
        Magasin magasin = new Magasin();
        // Attribut de l'objet retourné.
        int idMagasin;
        String nomMagasin;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT ma.idMagasin, ma.nom AS nomMagasin " +
                            "FROM magasin ma " +
                            "WHERE ma.idMagasin = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Recuperation des donnees.
                idMagasin = resultSet.getInt("idMagasin");
                nomMagasin = resultSet.getString("nomMagasin");
                // ajout des données dans magasin
                magasin.setIdMagasin(idMagasin);
                magasin.setNom(nomMagasin);
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

        return magasin;
    }

    @Override
    public void createMagasin(Magasin magasin) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO magasin (nom) VALUES (?)");

            preparedStatement.setString(1, magasin.getNom());

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
    public void updateMagasin(Integer id, Magasin magasin) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE magasin m SET m.nom = ? WHERE m.idMagasin = ?");

            preparedStatement.setString(1, magasin.getNom());
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
    public void deleteMagasin(Integer idMagasin) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM magasin WHERE idMagasin = ?");
            // Set attributes
            preparedStatement.setInt(1, idMagasin);
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
