package be.ifosup.servlet.categorie;

import be.ifosup.dao.DAOFactory;
import be.ifosup.categorie.Categorie;
import be.ifosup.categorie.CategorieDAO;
import be.ifosup.utils.FormsConstantes;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletAddCategorie", urlPatterns = "/add_categorie")
public class ServletAddCategorie extends HttpServlet {
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Récuperation de la mesure dans le formulaire.
        String nomCategorie = request.getParameter("nomCategorie");

        if (StringUtils.isBlank(nomCategorie)) {
            String error = encode("La catégorie ne peut pas être vide.","UTF-8");
            response.sendRedirect("categories?error=" + error);
        } else {
            try {
                // Ajout de la catégoie dans la db.
                categorieDAO.createCategorie(new Categorie(null, nomCategorie));
                request.setAttribute("categories", categorieDAO.getCategories());
                response.sendRedirect("categories");
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }
}
