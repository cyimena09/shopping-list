package be.ifosup.servlet.panier;

import be.ifosup.dao.DAOFactory;
import be.ifosup.panier.Panier;
import be.ifosup.panier.PanierDAO;
import be.ifosup.produit.Produit;
import be.ifosup.produit.ProduitDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletListPaniers", urlPatterns = "/paniers")
public class ServletListPanier extends HttpServlet {
    private PanierDAO panierDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("paniers", this.panierDAO.getPaniers());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/panier/paniers.jsp").forward(request, response);
    }

}
