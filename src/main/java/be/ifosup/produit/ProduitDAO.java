package be.ifosup.produit;

import java.sql.SQLException;
import java.util.List;

public interface ProduitDAO {

    List<Produit> getProduits() throws SQLException;

    void createProduit(Produit produit);

    void updateProduit(Long id, Produit produit);

    void deleteProduit(Long id);

}
