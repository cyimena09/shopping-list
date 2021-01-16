package be.ifosup.mesure;

import be.ifosup.dao.DAOFactory;
import be.ifosup.produit.Produit;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MesureDAOImpl implements MesureDAO {
    // ATTRIBUTES
    private final DAOFactory daoFactory;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Statement statement = null;
    ResultSet resultSet = null;

    // CONSTRUCTOR
    public MesureDAOImpl(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    // METHODS
    @Override
    public List<Mesure> getMesures() throws SQLException {
        List<Mesure> mesures = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT m.idMesure, m.nom FROM mesure m");

        while (resultSet.next()) {
            Long idMesure = resultSet.getLong("idMesure");
            String nom = resultSet.getString("nom");

            Mesure mesure = new Mesure(idMesure, nom);
            mesures.add(mesure);
        }
        return mesures;
    }

    @Override
    public void createMesure(Mesure mesure) {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO mesure (nom) VALUES (?)");

            preparedStatement.setString(1, mesure.getNom());

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void updateMesure(Long id, Mesure mesure) {
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE mesure m SET m.nom = ? WHERE m.idMesure = ?");

            preparedStatement.setString(1, mesure.getNom());
            preparedStatement.setLong(2, mesure.getIdMesure());

            preparedStatement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    public void deleteMesure(Long id) {

    }

}
