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

@WebServlet(name = "ServletUpdateProduitInPanier", urlPatterns = "/update_produit_in_panier")
public class ServletUpdateProduitInPanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        Integer idPanier = null;
        String strQuantite = request.getParameter("quantite");
        // Nous autorisons l'utilisateur à encoder un nombre décimale en utilisant la virgule ou le point.
        // S'il choisi la virgule on le converti en point
        strQuantite = strQuantite.replace(",", ".");

        try {
            // Récupération des paramètres depuis l'url et la méthode post.
            int idPanierProduit = Integer.parseInt(request.getParameter("idPanierProduit"));
            idPanier = Integer.parseInt(request.getParameter("idPanier"));
            float quantite = Float.parseFloat(strQuantite);
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
