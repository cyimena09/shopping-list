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

import static java.net.URLEncoder.encode;

@WebServlet(name = "ServletUpdatePanier", urlPatterns = "/update_panier")
public class ServletUpdatePanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        Integer idPanier = null;

        try {
            // Récupération des paramètres depuis l'url et la méthode post.
            int idPanierProduit = Integer.parseInt(request.getParameter("idPanierProduit"));
            idPanier = Integer.parseInt(request.getParameter("idPanier"));
            float quantite = Float.parseFloat(request.getParameter("quantite"));
            panierDAO.updateProduitInPanier(idPanierProduit, quantite);
            response.sendRedirect("single_panier?idPanier=" + idPanier);
        } catch (NumberFormatException e) {
            String error = encode("La quantité n'est pas valide.", "UTF-8");
            e.printStackTrace();
            response.sendRedirect("single_panier?idPanier=" + idPanier + "&error=" + error);
        } catch (SQLException throwable) {
            String error = encode("Impossible de mettre à jour le produit. Une quantité a au maximum trois décimales.", "UTF-8");
            throwable.printStackTrace();
            response.sendRedirect("single_panier?idPanier=" + idPanier + "&error=" + error);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdPanier = request.getParameter("idPanier");

        try {
            // Ajout de la mesure dans l'attribut.
            request.setAttribute("panier", panierDAO.getPanierById(Integer.parseInt(strIdPanier)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/update_panier.jsp").forward(request, response);
    }

}
