package be.ifosup.servlet.panier;

import be.ifosup.panier.Panier;
import be.ifosup.panier.PanierDAO;
import be.ifosup.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

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
        try {
            // Récupération des paramètres depuis l'url et la méthode post.
            int idPanierProduit = Integer.parseInt(request.getParameter("idPanierProduit"));
            int idPanier = Integer.parseInt(request.getParameter("idPanier"));
            float quantite = Float.parseFloat(request.getParameter("quantite"));
            panierDAO.updateProduitInPanier(idPanierProduit, quantite);

            response.sendRedirect("single_panier?idPanier=" + idPanier);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
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
