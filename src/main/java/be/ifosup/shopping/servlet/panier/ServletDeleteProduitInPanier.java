package be.ifosup.shopping.servlet.panier;

import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.panier.PanierDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteProduitInPanier", urlPatterns = "/delete_produit_in_panier")
public class ServletDeleteProduitInPanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int idPanierProduit = Integer.parseInt(request.getParameter("idPanierProduit"));
            int idPanier = Integer.parseInt(request.getParameter("idPanier"));
            String view = request.getParameter("view");
            // On appelle la méthode delete.
            panierDAO.deleteProduitInPanier(idPanierProduit);
            // Redirection en fonction de la vue où a été effectué la suppression.
            if (view != null && view.equals("paniers")) {
                response.sendRedirect("paniers");
            } else {
                response.sendRedirect("single_panier?idPanier=" + idPanier);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            //response.sendRedirect("single_panier?idPanier=" + idPanier);
        }
    }

}
