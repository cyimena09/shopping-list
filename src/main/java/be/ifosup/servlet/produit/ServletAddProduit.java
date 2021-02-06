package be.ifosup.servlet.produit;

import be.ifosup.dao.DAOFactory;
import be.ifosup.mesure.MesureDAO;
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

@WebServlet(name = "ServletAddProduit", urlPatterns = "/add_produit")
public class ServletAddProduit extends HttpServlet {
    private PanierDAO panierDAO;
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.panierDAO = daoFactory.getPanierDAO();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    // Cette méthode vise à ajouter un produit dans le panier
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recupération du panier, du produit et du magasin depuis l'url
        Integer idPanier = Integer.parseInt(request.getParameter("idPanier"));
        Integer idProduit = Integer.parseInt(request.getParameter("idProduit"));
        Integer idMagasin = Integer.parseInt(request.getParameter("idMagasin"));

        System.out.println("LID DU PANIER EST : " + idPanier +"LID DU MAGASIN EST : "+ idMagasin + "LID DU PRODUIT EST :" +  idProduit);
        try {
            panierDAO.addProduitInPanier(idPanier,idMagasin, idProduit, 5 );
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        response.sendRedirect("single_panier?idPanier=2");
        //request.getRequestDispatcher("views/magasin/update_magasin.jsp").forward(request, response);
    }

}