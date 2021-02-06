package be.ifosup.servlet.panier;

import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.MagasinDAO;
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

@WebServlet(name = "ServletAddPanier", urlPatterns = "/add_panier")
public class ServletAddPanier extends HttpServlet {
    private PanierDAO panierDAO;
    private MagasinDAO magasinDAO;
    private ProduitDAO produitDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.magasinDAO = daoFactory.getMagasinDAO();
        this.produitDAO = daoFactory.getProduitDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");

        // Recuperation et conversion en Integer de l'id du magasin et du produit.
        Integer idMagasin = Integer.parseInt(request.getParameter("idMagasin"));

        try {
            // Ajout de la mesure dans la db.
            panierDAO.createPanier(new Panier(idMagasin));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        // On appelle le servletListPanier qui va se charger de récupérer les paniers
        response.sendRedirect("paniers");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("magasins", magasinDAO.getMagasins());
            request.setAttribute("produits", produitDAO.getProduits());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/panier/add_panier.jsp").forward(request, response);
    }

}
