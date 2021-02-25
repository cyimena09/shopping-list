package be.ifosup.servlet.panier;

import be.ifosup.categorie.CategorieDAO;
import be.ifosup.dao.DAOFactory;
import be.ifosup.panier.PanierDAO;
import be.ifosup.produit.ProduitDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletSinglePanier", urlPatterns = "/single_panier")
public class ServletSinglePanier extends HttpServlet {
    private PanierDAO panierDAO;
    private ProduitDAO produitDAO;
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.produitDAO = daoFactory.getProduitDAO();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        String warning = request.getParameter("warning");

        try {
            // On récupère le panier qui correspond à l'id indiqué dans l'url de la requête.
            Integer idPanier = Integer.parseInt(request.getParameter("idPanier"));
            request.setAttribute("panier", panierDAO.getPanierById(idPanier));
            request.setAttribute("produits", produitDAO.getProduitsByPanierId(idPanier));
            request.setAttribute("allProduits", produitDAO.getProduits());
            request.setAttribute("categories", categorieDAO.getCategoriesIfProductExist());
            // Le servlet addPanierProduit retourne un warning
            // si on a essayé de rajouter un produit qui existe déjà dans le panier.
            if (warning != null) {
                request.setAttribute("warning", warning);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/single_panier.jsp").forward(request, response);
    }
}
