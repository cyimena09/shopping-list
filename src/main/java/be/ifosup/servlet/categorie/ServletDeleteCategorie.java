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

@WebServlet(name = "ServletDeleteMesure", urlPatterns = "/delete_mesure")
public class ServletDeleteCategorie extends HttpServlet {
    // ATTRIBUTS
    private CategorieDAO categorieDAO;

    // METHODES
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id de la catégorie à supprimer.
        String idMesure = request.getParameter("idMesure");

        try {
            // On appelle la méthode delete.
            categorieDAO.deleteCategorie(Integer.parseInt(idMesure));
            request.setAttribute("catégories", categorieDAO.getCategories());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/mesure/mesures.jsp").forward(request, response);
    }

}
