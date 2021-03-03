package be.ifosup.shopping.panier;

import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.magasin.Magasin;
import be.ifosup.shopping.produit.Produit;
import be.ifosup.shopping.produit.ProduitDAO;

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
        // On appelle le dao de produit pour récupérer les produits du panier
        DAOFactory daoFactory = DAOFactory.getInstance();
        ProduitDAO produitDAO = daoFactory.getProduitDAO();

        List<Panier> paniers = new ArrayList<>();
        List<Produit> produits;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(
                    "SELECT pa.idPanier, pa.nom AS nomPanier, ma.idMagasin, ma.nom AS nomMagasin " +
                            "FROM panier pa " +
                            "LEFT JOIN magasin ma ON ma.idMagasin = pa.idMagasin");

            while (resultSet.next()) {
                // Récupération
                Integer idPanier = resultSet.getInt("idPanier");
                String nomPanier = resultSet.getString("nomPanier");
                Integer idMagasin = resultSet.getInt("idMagasin");
                String nomMagasin = resultSet.getString("nomMagasin");
                // Magasin
                Magasin magasin = new Magasin(idMagasin, nomMagasin);
                // Produits
                produits = produitDAO.getProduitsByPanierId(idPanier);
                // Panier
                Panier panier = new Panier(idPanier, nomPanier, magasin, produits);
                // Ajout du panier dans la liste des paniers
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
        // On appelle le dao de produit pour récupérer les produits du panier
        DAOFactory daoFactory = DAOFactory.getInstance();
        ProduitDAO produitDAO = daoFactory.getProduitDAO();
        // On instancie nos classes
        Panier panier = null;
        Magasin magasin;
        List<Produit> produits;
        // Déclaration des variables qui seront utilisées dans le résultat de la recherche.
        Integer idPanier;
        String nomPanier;
        Integer idMagasin;
        String nomMagasin ;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT pa.idPanier, pa.nom as nomPanier, ma.idMagasin, ma.nom as nomMagasin " +
                            "FROM panier pa " +
                            "INNER JOIN magasin ma ON ma.idMagasin = pa.idMagasin " +
                            "WHERE pa.idPanier = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            if (resultSet.next()) {
                // de la table panier
                idPanier = resultSet.getInt("idPanier");
                nomPanier = resultSet.getString("nomPanier");
                idMagasin = resultSet.getInt("idMagasin");
                nomMagasin = resultSet.getString("nomMagasin");

                // Magasin
                magasin = new Magasin(idMagasin, nomMagasin);
                // Produits
                produits = produitDAO.getProduitsByPanierId(idPanier);
                // Panier
                panier = new Panier(idPanier, nomPanier, magasin, produits);
            }
            return panier;
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        } finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // Permet de savoir si un produit existe déjà dans un panier
    @Override
    public Integer searchproduitInPanier(int idPanier, int idProduit) throws SQLException {
        // On appelle le dao de produit pour récupérer les produits du panier
        // Variable.
        Integer idPanierProduit = null;

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT pp.idPanierProduit " +
                            "FROM panier_produit pp " +
                            "WHERE pp.idPanier = ? AND pp.idProduit = ?");
            // Set attributes.
            preparedStatement.setInt(1, idPanier);
            preparedStatement.setInt(2, idProduit);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            while (resultSet.next()) {
                // de la table panier
                idPanierProduit = resultSet.getInt("idPanierProduit");
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

        return idPanierProduit;
    }

    @Override
    public void createPanier(Panier panier) throws SQLException {
        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO panier (nom, idMagasin) " +
                            "VALUES (?, ?)");

            preparedStatement.setString(1, panier.getNom());
            preparedStatement.setInt(2, panier.getMagasin().getIdMagasin());

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
    public void updatePanierNom(Integer id, String nom) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("UPDATE panier p SET p.nom = ? WHERE p.idPanier = ?");

            preparedStatement.setString(1, nom);
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
    public void updateProduitInPanier(int idPanierProduit, float quantite) throws SQLException {

        try {
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                    "UPDATE panier_produit pp " +
                            "SET pp.quantite = ? " +
                            "WHERE pp.idPanierProduit = ?");

            preparedStatement.setFloat(1, quantite);
            preparedStatement.setInt(2, idPanierProduit);

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
    public void deleteProduitInPanier(int idPanierProduit) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement("DELETE FROM panier_produit WHERE idPanierProduit = ?");
            // Set attributes
            preparedStatement.setInt(1, idPanierProduit);
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

    // Lorsqu'on supprime un panier on supprime égalemment le panier dans la table intermédiaire
    @Override
    public void deletePanier(Integer idPanier) throws SQLException {

        try {
            // La connexion et la requete préparée sont créés.
            connection = daoFactory.getConnection();

            // Suppression du panier
            preparedStatement = connection.prepareStatement("DELETE FROM panier WHERE idPanier = ?");
            // Set attributes
            preparedStatement.setInt(1, idPanier);
            // Execution de la requete.
            preparedStatement.executeUpdate();

            // Suppression du panier_produit
            preparedStatement = connection.prepareStatement("DELETE FROM panier_produit WHERE idPanier = ?");
            preparedStatement.setInt(1, idPanier);
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
