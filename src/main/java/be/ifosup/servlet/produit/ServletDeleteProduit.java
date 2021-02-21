package be.ifosup.servlet.produit;

import be.ifosup.categorie.CategorieDAO;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.dao.DAOFactory;
import be.ifosup.produit.ProduitDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteProduit", urlPatterns = "/delete_produit")
public class ServletDeleteProduit extends HttpServlet {
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;
    private CategorieDAO categorieDAO;

    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idProduit = request.getParameter("idProduit");

        try {
            produitDAO.deleteProduit(Integer.parseInt(idProduit));
            request.setAttribute("produits", produitDAO.getProduits());
            request.setAttribute("mesures", mesureDAO.getMesures());
            request.setAttribute("categories", categorieDAO.getCategories());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/produit/produits.jsp").forward(request, response);
    }

}
