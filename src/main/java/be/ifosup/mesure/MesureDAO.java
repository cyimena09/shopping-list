package be.ifosup.mesure;

import java.sql.SQLException;
import java.util.List;

public interface MesureDAO {

    List<Mesure> getMesures() throws SQLException;

    void createMesure(Mesure mesure);

    void updateMesure(Long id, Mesure mesure);

    void deleteMesure(Long id);

}
