package be.ifosup.servlet;

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

@WebServlet(name = "ServletDeleteMesure", urlPatterns = "/delete_mesure")
public class ServletDeleteMesure extends HttpServlet {
    // ATTRIBUTS
    private MesureDAO mesureDAO;

    // METHODES
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Récupération de l'id de le mesure à supprimer.
        String idMesure = request.getParameter("idMesure");

        try {
            // On appelle la méthode delete.
            mesureDAO.deleteMesure(Integer.parseInt(idMesure));
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/mesures.jsp").forward(request, response);
    }

}
