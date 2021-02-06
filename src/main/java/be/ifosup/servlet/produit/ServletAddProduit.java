package be.ifosup.servlet.produit;

import be.ifosup.dao.DAOFactory;
import be.ifosup.mesure.MesureDAO;
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
    private ProduitDAO produitDAO;
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.produitDAO = daoFactory.getProduitDAO();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // force UTF-8
        request.setCharacterEncoding("UTF-8");
        // get form values
        String nomProduit = request.getParameter("nomProduit");
        String strIdCategorie = request.getParameter("idCategorie");
        String strIdMesure = request.getParameter("idMesure");

        // convert strings to long
        //Long idCategorie = Long.parseLong(strIdCategorie);
        Integer idMesure = Integer.parseInt(strIdMesure);

        // redirection
        try {
            // add in db
            produitDAO.createProduit(new Produit(null, nomProduit, 1, idMesure ));
            request.setAttribute("produits", produitDAO.getProduits());
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/produit/produits.jsp").forward(request, response);
    }

}