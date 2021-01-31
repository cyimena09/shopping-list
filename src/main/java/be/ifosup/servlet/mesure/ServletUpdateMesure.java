package be.ifosup.servlet.mesure;

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

@WebServlet(name = "ServletUpdateMesure", urlPatterns = "/update_mesure")
public class ServletUpdateMesure extends HttpServlet {
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8
        request.setCharacterEncoding("UTF-8");
        // Récupération depuis l'url et conversion en Integer de l'id de la mesure.
        String strIdMesure = request.getParameter("idMesure");
        Integer idMesure = Integer.parseInt(strIdMesure);
        // Récupération du nom de la mesure depuis le formulaire.
        String nomMesure = request.getParameter("nomMesure");

        try {
            mesureDAO.updateMesure(idMesure, new Mesure(null, nomMesure));
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/mesures.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdMesure = request.getParameter("idMesure");

        try {
            // Ajout de la mesure dans l'attribut.
            request.setAttribute("mesure", mesureDAO.getMesureById(Integer.parseInt(strIdMesure)));
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/update_mesure.jsp").forward(request, response);
    }
}