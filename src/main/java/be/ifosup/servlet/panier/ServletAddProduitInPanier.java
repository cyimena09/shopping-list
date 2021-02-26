package be.ifosup.servlet.panier;

import be.ifosup.panier.PanierDAO;
import be.ifosup.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import org.apache.commons.lang3.StringUtils;

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletAddProduitInPanier", urlPatterns = "/add_produit_in_panier")
public class ServletAddProduitInPanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    // Cette méthode vise à ajouter un produit dans le panier
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Récupération du panier, du produit et du magasin depuis l'url
        int idPanier = Integer.parseInt(request.getParameter("idPanier"));
        int idProduit = Integer.parseInt(request.getParameter("idProduit"));

        try {
            // On vérifie si le produit est déjà dans le panier, s'il n'est pas dans le panier on ajoute une unité.
            if (panierDAO.searchproduitInPanier(idPanier, idProduit) == null) {
                panierDAO.addProduitInPanier(idPanier, idProduit, 1);
                response.sendRedirect("single_panier?idPanier=" + idPanier);
            } else {
                String warning = encode("Le produit existe déjà dans le panier.", "UTF-8");
                response.sendRedirect("single_panier?idPanier=" + idPanier + "&warning=" + warning);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
