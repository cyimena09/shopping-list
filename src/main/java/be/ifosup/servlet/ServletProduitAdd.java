package be.ifosup.servlet;

import be.ifosup.dao.DAOFactory;
import be.ifosup.produit.Produit;
import be.ifosup.produit.ProduitDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletProduitAdd", urlPatterns = "/add_produit")
public class ServletProduitAdd extends HttpServlet {
    private ProduitDAO produitDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");
        // get form values
        String nomPro = request.getParameter("nomPro");
        // add in db
        produitDAO.createProduit(new Produit((long)5, nomPro, (long)1,(long)1 ));
        // redirection
        try {
            request.setAttribute("produits", produitDAO.getProduits());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/produits.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/add_produit.jsp").forward(request, response);
    }
}
