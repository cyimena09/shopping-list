package be.ifosup.servlet.panier;

import be.ifosup.dao.DAOFactory;
import be.ifosup.mesure.Mesure;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.produit.Produit;
import be.ifosup.produit.ProduitDAO;
import be.ifosup.panier.Panier;
import be.ifosup.panier.PanierDAO;

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
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Récupération depuis l'url et conversion en Integer de l'id de la mesure.
        Integer idPanier = Integer.parseInt(request.getParameter("idPanier"));

        // Récupération depuis le formulaire.
        String nomPanier = request.getParameter("nomPanier");
        Integer idMagasin = Integer.parseInt(request.getParameter("idMagasin"));
//        Integer idProduit = Integer.parseInt(request.getParameter("idProduit"));
//        Integer quantite = Integer.parseInt(request.getParameter("quantite"));
        try {
            panierDAO.updatePanier(idPanier, new Panier());
            request.setAttribute("paniers", panierDAO.getPaniers());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/paniers.jsp").forward(request, response);
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
