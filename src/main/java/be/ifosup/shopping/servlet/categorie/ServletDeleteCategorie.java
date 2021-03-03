package be.ifosup.shopping.servlet.categorie;

import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.categorie.CategorieDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteCategorie", urlPatterns = "/delete_categorie")
public class ServletDeleteCategorie extends HttpServlet {
    private CategorieDAO categorieDAO;

    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id du categorie à supprimer.
        String idCategorie = request.getParameter("idCategorie");

        try {
            // On appelle la méthode delete.
            categorieDAO.deleteCategorie(Integer.parseInt(idCategorie));
            request.setAttribute("categories", categorieDAO.getCategories());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/categorie/categories.jsp").forward(request, response);
    }
}
