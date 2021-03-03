package be.ifosup.shopping.servlet.produit;

import be.ifosup.shopping.categorie.CategorieDAO;
import be.ifosup.shopping.daoFactory.DAOFactory;
import be.ifosup.shopping.mesure.MesureDAO;
import be.ifosup.shopping.produit.ProduitDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletListProduits", urlPatterns = "/produits")
public class ServletListProduits extends HttpServlet {
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;
    private CategorieDAO categorieDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
        this.categorieDAO = daoFactory.getCategorieDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String error = request.getParameter("error");
        try {
            request.setAttribute("produits", produitDAO.getProduits());
            request.setAttribute("mesures", mesureDAO.getMesures());
            request.setAttribute("categories", categorieDAO.getCategories());
            request.setAttribute("error", error);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/produit/produits.jsp").forward(request, response);
    }

}
