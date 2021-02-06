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

@WebServlet(name = "ServletDeletePanier", urlPatterns = "/delete_panier")
public class ServletDeletePanier extends HttpServlet {
    // ATTRIBUTS
    private PanierDAO panierDAO;

    // METHODES
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération de l'id de le mesure à supprimer.
        String idMesure = request.getParameter("idPanier");

        try {
            // On appelle la méthode delete.
            panierDAO.deletePanier(Integer.parseInt(idMesure));
            request.setAttribute("paniers", panierDAO.getPaniers());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/panier/paniers.jsp").forward(request, response);
    }

}
