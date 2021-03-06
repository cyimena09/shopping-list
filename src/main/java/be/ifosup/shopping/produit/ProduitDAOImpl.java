package be.ifosup.shopping.produit;

import be.ifosup.shopping.categorie.Categorie;
import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.mesure.Mesure;

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
            resultSet = statement.executeQuery(
                    "SELECT pr.idProduit, pr.nom AS nomProduit, ca.idCategorie, ca.nom AS nomCategorie, me.idMesure, me.nom AS nomMesure " +
                            "FROM produit pr " +
                            "INNER JOIN mesure me ON me.idMesure = pr.idMesure " +
                            "INNER JOIN categorie ca ON ca.idCategorie = pr.idCategorie");
            // Recuperation des donnees.
            while (resultSet.next()) {
                // Recupération dans la table
                Integer idProduit = resultSet.getInt("idProduit");
                String nomProduit = resultSet.getString("nomProduit");
                Integer idCategorie = resultSet.getInt("idCategorie");
                String nomCategorie = resultSet.getString("nomCategorie");
                Integer idMesure = resultSet.getInt("idMesure");
                String nomMesure = resultSet.getString("nomMesure");

                // Ajout dans categorie
                Categorie categorie = new Categorie();
                categorie.setIdCategorie(idCategorie);
                categorie.setNom(nomCategorie);
                // Ajout dans mesure
                Mesure mesure = new Mesure();
                mesure.setIdMesure(idMesure);
                mesure.setNom(nomMesure);
                // Ajout dans produit
                Produit produit = new Produit();
                produit.setIdProduit(idProduit);
                produit.setNom(nomProduit);
                produit.setCategorie(categorie);
                produit.setMesure(mesure);
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
        Categorie categorie = new Categorie();
        Mesure mesure = new Mesure();
        Produit produit = new Produit();
        // Attribut de l'objet retourné.
        int idProduit;
        String nomProduit;
        int idMesure;
        String nomMesure;
        int idCategorie;
        String nomCategorie;

        try {
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT pr.idProduit, pr.nom AS nomProduit, ca.idCategorie, ca.nom AS nomCategorie, me.idMesure, me.nom AS nomMesure " +
                            "FROM produit pr " +
                            "INNER JOIN mesure me ON me.idMesure = pr.idMesure " +
                            "INNER JOIN categorie ca ON ca.idCategorie = pr.idCategorie " +
                            "WHERE pr.idProduit = ?");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            if (resultSet.next()) {
                idProduit = resultSet.getInt("idProduit");
                nomProduit = resultSet.getString("nomProduit");
                idMesure = resultSet.getInt("idMesure");
                nomMesure = resultSet.getString("nomMesure");
                idCategorie = resultSet.getInt("idCategorie");
                nomCategorie = resultSet.getString("nomCategorie");
                // Ajout dans catégorie
                categorie.setIdCategorie(idCategorie);
                categorie.setNom(nomCategorie);
                // Ajout dans mesure
                mesure.setIdMesure(idMesure);
                mesure.setNom(nomMesure);
                // Ajout dans produit
                produit.setIdProduit(idProduit);
                produit.setNom(nomProduit);
                produit.setMesure(mesure);
                produit.setCategorie(categorie);
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

        return produit;
    }

    @Override
    public List<Produit> getProduitsByPanierId(Integer id) throws SQLException {
        List<Produit> produitsList = new ArrayList<>();
        // Attributs de l'objet retourné.
        int idProduit;
        String nomProduit;
        int idPanierProduit;
        float quantite;
        int idMesure;
        String nomMesure;

        try {
            // La connexion et la requete prepare sont crées.
            connection = daoFactory.getConnection();
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(
                    "SELECT pr.idProduit, pr.nom AS nomProduit, pp.idPanierProduit, pp.quantite, me.idMesure, me.nom AS nomMesure " +
                            "FROM produit pr " +
                            "INNER JOIN panier_produit pp ON pp.idProduit = pr.idProduit " +
                            "INNER JOIN mesure me ON me.idMesure = pr.idMesure " +
                            "WHERE pp.idPanier = ? " +
                            "GROUP BY pp.idProduit");
            // Set attributes.
            preparedStatement.setInt(1, id);
            // Execution de la requete.
            // On crée une nouvelle variable resultSet pour le différencier de l'attribut de la classe resultSet
            // car cette méthode est imbriqué dans une autre methode et les deux resultset ne concernent pas la meme requete
            ResultSet resultSet = preparedStatement.executeQuery();
            // Recuperation des donnees.
            while (resultSet.next()) {
                Produit produit = new Produit();
                Mesure mesure = new Mesure();
                // Récupération des données
                idProduit = resultSet.getInt("idProduit");
                nomProduit = resultSet.getString("nomProduit");
                idPanierProduit = resultSet.getInt("idPanierProduit");
                quantite = resultSet.getFloat("quantite");
                idMesure = resultSet.getInt("idMesure");
                nomMesure = resultSet.getString("nomMesure");
                // On ajout les données dans mesure
                mesure.setIdMesure(idMesure);
                mesure.setNom(nomMesure);
                // On ajout les données dans produit
                produit.setIdProduit(idProduit);
                produit.setNom(nomProduit);
                produit.setIdPanierProduit(idPanierProduit);
                produit.setQuantite(quantite);
                produit.setMesure(mesure);
                // Ajout du produit dans la liste des produits
                produitsList.add(produit);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                preparedStatement.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return produitsList;
    }

    @Override
    public void createProduit(Produit produit) throws SQLException {

        try {
            // La connexion et la requete prepare sont crees.
            connection = daoFactory.getConnection();
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO produit (nom, idMesure, idCategorie) VALUES (?, ?, ?)");
            // Set attributes
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setInt(2, produit.getMesure().getIdMesure());
            preparedStatement.setInt(3, produit.getCategorie().getIdCategorie());
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
            preparedStatement = connection.prepareStatement(
                    "UPDATE produit pr " +
                            "SET pr.nom = ?, pr.idMesure = ?, pr.idCategorie = ? " +
                            "WHERE pr.idProduit = ?");
            // Set attributes
            preparedStatement.setString(1, produit.getNom());
            preparedStatement.setInt(2, produit.getMesure().getIdMesure());
            preparedStatement.setInt(3, produit.getCategorie().getIdCategorie());
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

            // Suppression dans le panier_produit
            preparedStatement = connection.prepareStatement("DELETE FROM panier_produit WHERE idPanier = ?");
            preparedStatement.setInt(1, idProduit);
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
