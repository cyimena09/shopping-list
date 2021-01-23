package be.ifosup.produit;

import java.sql.SQLException;
import java.util.List;

public interface ProduitDAO {

    List<Produit> getProduits() throws SQLException;

    Produit getProduitById(Integer idPorduit)  throws SQLException;

    void createProduit(Produit produit) throws SQLException;

    void updateProduit(Integer idProduit, Produit produit) throws SQLException;

    void deleteProduit(Integer idProduit) throws SQLException;

}
