package be.ifosup.servlet.categorie;

import be.ifosup.dao.DAOFactory;
import be.ifosup.categorie.Categorie;
import be.ifosup.categorie.CategorieDAO;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletUpdateCategorie", urlPatterns = "/update_categorie")
public class ServletUpdateCategorie extends HttpServlet {
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Récupération depuis l'url et conversion en Integer de l'id du categorie.
        String strIdCategorie = request.getParameter("idCategorie");
        Integer idCategorie = Integer.parseInt(strIdCategorie);
        // Récupération du nom du categorie depuis le formulaire.
        String nomCategorie = request.getParameter("nomCategorie");

        if (StringUtils.isBlank(nomCategorie)) {
            String error = encode("Le champs ne peut pas être vide.", "UTF-8");
            response.sendRedirect("categories?error=" + error);
        } else {
            try {
                categorieDAO.updateCategorie(idCategorie, new Categorie(null, nomCategorie));
                request.setAttribute("categories", categorieDAO.getCategories());
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }

            request.getRequestDispatcher("views/categorie/categories.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdCategorie = request.getParameter("idCategorie");

        try {
            // Ajout du categorie dans l'attribut.
            request.setAttribute("categorie", categorieDAO.getCategorieById(Integer.parseInt(strIdCategorie)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/categorie/update_categorie.jsp").forward(request, response);
    }

}
