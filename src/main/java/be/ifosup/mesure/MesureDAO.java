package be.ifosup.mesure;

import be.ifosup.produit.Produit;

import java.sql.SQLException;
import java.util.List;

public interface MesureDAO {

    List<Mesure> getMesures() throws SQLException;

    void createMesure(Produit produit);

    void updateMesure(Long id);

    void deleteMesure(Long id);

}
