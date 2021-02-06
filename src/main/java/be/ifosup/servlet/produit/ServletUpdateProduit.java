package be.ifosup.servlet.produit;

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

@WebServlet(name = "ServletUpdateProduit", urlPatterns = "/update_produit")
public class ServletUpdateProduit extends HttpServlet {
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Récupération de l'id du produit depuis l'url.
        Integer idProduit = Integer.parseInt(request.getParameter("idProduit"));
        // Récupération du nom de la mesure depuis le formulaire.
        String nomProduit = request.getParameter("nomProduit");
        Integer idMesure = Integer.parseInt(request.getParameter("idMesure"));

        try {
            produitDAO.updateProduit(idProduit, new Produit(null, nomProduit, 2, idMesure));
            request.setAttribute("produits", produitDAO.getProduits());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/produit/produits.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdProduit = request.getParameter("idProduit");
        try {
            // Recuperation du produits, des catégories et des mesures.
            request.setAttribute("produit", produitDAO.getProduitById(Integer.parseInt(strIdProduit)));
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/produit/update_produit.jsp").forward(request, response);
    }

}
