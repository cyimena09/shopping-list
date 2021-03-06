package be.ifosup.shopping.categorie;

import java.sql.SQLException;
import java.util.List;

public interface CategorieDAO {

    List<Categorie> getCategories() throws SQLException;

    List<Categorie> getCategoriesIfProductExist() throws SQLException;

    Categorie getCategorieById(Integer id) throws SQLException;

    void createCategorie(Categorie magasin) throws SQLException;

    void updateCategorie(Integer id, Categorie magasin) throws SQLException;

    void deleteCategorie(Integer id) throws SQLException;

}
