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

    @Override
    public List<Mesure> getMesures() throws SQLException {
        List<Mesure> mesures = new ArrayList<>();

        connection = daoFactory.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery(
                "SELECT m.idMesure, m.nom FROM mesure m");

        while (resultSet.next()) {
            Long idMesure = resultSet.getLong("idMesure");
            String nom = resultSet.getString("nom");

            Mesure mesure = new Mesure(idMesure, nom);
            mesures.add(mesure);
        }

        return mesures;
    }

    @Override
    public void createMesure(Produit produit) {

    }

    @Override
    public void updateMesure(Long id) {

    }

    @Override
    public void deleteMesure(Long id) {

    }
}
