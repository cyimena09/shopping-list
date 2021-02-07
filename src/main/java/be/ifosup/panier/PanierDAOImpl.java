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
            resultSet = statement.executeQuery(
                    "SELECT pa.idPanier, pa.nom as nomPanier, pp.quantite " +
                            "FROM panier pa " +
                            "LEFT JOIN panier_produit pp ON pp.idPanier = pa.idPanier");

            while (resultSet.next()) {
                Integer idPanier = resultSet.getInt("idPanier");
                String nomPanier = resultSet.getString("nomPanier");

                Panier panier = new Panier();
                panier.setIdPanier(idPanier);
                panier.setNom(nomPanier);

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
        String nomPanier = null;
        Integer quantite = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT pa.idPanier, pa.nom as nomPanier, pp.quantite, pr.nom as nomProduit " +
                    "FROM panier pa " +
                    "LEFT JOIN panier_produit pp ON pp.idPanier = pa.idPanier " +
                    "INNER JOIN produit pr on pr.idProduit = pp.idProduit " +
                    "WHERE pa.idPanier = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            while (resultSet.next()) {
                // de la table panier
                idPanier = resultSet.getInt("idPanier");
                nomPanier = resultSet.getString("nomPanier");
                // de la table panier_produit
                quantite = resultSet.getInt("quantite");
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

        Panier panier = new Panier();
        panier.setIdPanier(idPanier);
        panier.setNom(nomPanier);
        panier.setQuantite(quantite);

        return panier;
    }

    @Override
    public void createPanier(Panier panier) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO panier (nom, idMagasin) " +
                    "VALUES (?, ?)");

            preparedStatement.setString(1, panier.getNom());
            preparedStatement.setInt(2, panier.getIdMagasin());

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
    public void addProduitInPanier(Integer idPanier, Integer idProduit, Integer quantite) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO panier_produit (idPanier, idProduit, quantite) " +
                    "VALUES (?, ?, ?)");

            preparedStatement.setInt(1, idPanier);
            preparedStatement.setInt(2, idProduit);
            preparedStatement.setInt(3, quantite);

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

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE panier m SET m.nom = ? WHERE m.idPanier = ?");

           // preparedStatement.setString(1, panier.getNom());
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
