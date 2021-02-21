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

@WebServlet(name = "ServletDeletePanier", urlPatterns = "/delete_panier")
public class ServletDeletePanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id de le mesure à supprimer.
        Integer idPanier = Integer.parseInt(request.getParameter("idPanier"));

        try {
            // On appelle la méthode delete.
            panierDAO.deletePanier(idPanier);
            request.setAttribute("paniers", panierDAO.getPaniers());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/panier/paniers.jsp").forward(request, response);
    }

}
