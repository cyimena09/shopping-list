package be.ifosup.servlet.panier;

import be.ifosup.dao.DAOFactory;
import be.ifosup.mesure.MesureDAO;
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

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.produitDAO = daoFactory.getProduitDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            // On récupère le panier indiqué dans l'url de la requete
            Integer idPanier = Integer.parseInt( request.getParameter("idPanier"));
            request.setAttribute("panier", panierDAO.getPanierById(idPanier));
            request.setAttribute("allProduits", produitDAO.getProduits());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/single_panier.jsp").forward(request, response);
    }
}
