package be.ifosup.servlet.magasin;

import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.MagasinDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteMagasin", urlPatterns = "/delete_magasin")
public class ServletDeleteMagasin extends HttpServlet {
    // ATTRIBUTS
    private MagasinDAO magasinDAO;

    // METHODES
    public void init(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id de le mesure à supprimer.
        String idMagasin = request.getParameter("idMagasin");

        try {
            // On appelle la méthode delete.
            magasinDAO.deleteMagasin(Integer.parseInt(idMagasin));
            request.setAttribute("magasins", magasinDAO.getMagasins());
        } catch (SQLException throwable){
            throwable.printStackTrace();
        }
        request.getRequestDispatcher("views/magasin/magasins.jsp").forward(request, response);
    }
}
