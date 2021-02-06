package be.ifosup.magasin;

import java.sql.SQLException;
import java.util.List;

public interface MagasinDAO {

    List<Magasin> getMagasins() throws SQLException;

    Magasin getMagasinById(Integer id) throws SQLException;

    void createMagasin(Magasin magasin) throws SQLException;

    void updateMagasin(Integer id, Magasin magasin) throws SQLException;

    void deleteMagasin(Integer id) throws SQLException;

}
