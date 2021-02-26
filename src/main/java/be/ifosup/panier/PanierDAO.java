package be.ifosup.panier;

import java.sql.SQLException;
import java.util.List;

public interface PanierDAO {

    List<Panier> getPaniers() throws SQLException;

    Panier getPanierById(Integer id) throws SQLException;

    // List<Produit> getProduitsByPanierId(Integer id) throws SQLException;

    Integer searchproduitInPanier(int idPanier, int idProduit) throws SQLException;

    void createPanier(Panier panier) throws SQLException;

    void addProduitInPanier(Integer idMagasin, Integer idProduit, Integer quantite) throws SQLException;

    void updatePanierNom(Integer id, String nom) throws SQLException;

    void updateProduitInPanier(int idPanierProduit, float quantite) throws SQLException;

    void deletePanier(Integer id) throws SQLException;

    public void deleteProduitInPanier(int idPanierProduit) throws SQLException;

}
