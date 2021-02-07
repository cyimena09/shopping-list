package be.ifosup.servlet.categorie;

import be.ifosup.dao.DAOFactory;
import be.ifosup.categorie.Categorie;
import be.ifosup.categorie.CategorieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAddCategorie", urlPatterns = "/add_categorie")
public class ServletAddCategorie extends HttpServlet {
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Recuperation de la mesure dans le formulaire.
        String nomCategorie = request.getParameter("nomCategorie");

        try {
            // Ajout de la mesure dans la db.
            categorieDAO.createCategorie(new Categorie(null, nomCategorie));
            request.setAttribute("categories", categorieDAO.getCategories());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/categorie/categories.jsp").forward(request, response);
    }

}
