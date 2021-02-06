package be.ifosup.categorie;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO {

    List<Categorie> getCategories() throws SQLException;

    Categorie getCategorieById(Integer id) throws SQLException;

    void createCategorie(Categorie categorie) throws SQLException;

    void updateCategorie(Integer id, Categorie categorie) throws SQLException;

    void deleteCategorie(Integer id) throws SQLException;

}
