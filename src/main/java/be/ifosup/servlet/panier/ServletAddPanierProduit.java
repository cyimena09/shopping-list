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

@WebServlet(name = "ServletAddProduit", urlPatterns = "/add_produit")
public class ServletAddPanierProduit extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    // Cette méthode vise à ajouter un produit dans le panier
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération du panier, du produit et du magasin depuis l'url
        Integer idPanier = Integer.parseInt(request.getParameter("idPanier"));
        Integer idProduit = Integer.parseInt(request.getParameter("idProduit"));

        try {
            panierDAO.addProduitInPanier(idPanier, idProduit, 5 );
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        response.sendRedirect("single_panier?idPanier=" + idPanier);
    }

}