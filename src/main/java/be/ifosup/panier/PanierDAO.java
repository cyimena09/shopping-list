package be.ifosup.panier;

import java.sql.SQLException;
import java.util.List;

public interface PanierDAO {

    List<Panier> getPaniers() throws SQLException;

    Panier getPanierById(Integer id) throws SQLException;

    void createPanier(Panier panier) throws SQLException;

    void updatePanier(Integer id, Panier panier) throws SQLException;

    void deletePanier(Integer id) throws SQLException;

}
