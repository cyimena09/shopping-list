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

@WebServlet(name = "ServletAddPanier", urlPatterns = "/add_panier")
public class ServletAddPanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Recuperation du panier dans le formulaire.
        String nomPanier = request.getParameter("nomPanier");

        try {
            // Ajout de la mesure dans la db.
            panierDAO.createPanier(new Panier(null));
            request.setAttribute("paniers", panierDAO.getPaniers());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/panier/paniers.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/panier/add_panier.jsp").forward(request, response);
    }

}
