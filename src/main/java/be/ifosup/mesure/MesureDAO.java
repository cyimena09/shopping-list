package be.ifosup.mesure;

import java.sql.SQLException;
import java.util.List;

public interface MesureDAO {

    List<Mesure> getMesures() throws SQLException;

    Mesure getMesureById(Integer id) throws SQLException;

    void createMesure(Mesure mesure) throws SQLException;

    void updateMesure(Integer id, Mesure mesure) throws SQLException;

    void deleteMesure(Integer id) throws SQLException;

}
