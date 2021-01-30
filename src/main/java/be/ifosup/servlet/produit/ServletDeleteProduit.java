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

@WebServlet(name = "ServletDeleteProduit", urlPatterns = "/delete_produit")
public class ServletDeleteProduit extends HttpServlet {
    // ATTRIBUTS
    private ProduitDAO produitDAO;

    // METHODES
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get idProduit
        String idProduit = request.getParameter("idProduit");

        try {
            // call delete method
            produitDAO.deleteProduit(Integer.parseInt(idProduit));
            request.setAttribute("produits", produitDAO.getProduits());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/produits.jsp").forward(request, response);
    }
}
