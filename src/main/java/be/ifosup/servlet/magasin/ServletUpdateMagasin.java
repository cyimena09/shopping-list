package be.ifosup.servlet.magasin;

import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.Magasin;
import be.ifosup.magasin.MagasinDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletUpdateMagasin", urlPatterns = "/update_magasin")
public class ServletUpdateMagasin extends HttpServlet {
    private MagasinDAO magasinDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Récupération de l'id depuis l'url.
        Integer idMagasin= Integer.parseInt(request.getParameter("idMagasin"));
        // Récupération du nom du magasin depuis le formulaire.
        String nomMagasin = request.getParameter("nomMagasin");

        // Ajout dans le magasin.
        Magasin magasin = new Magasin();
        magasin.setNom(nomMagasin);

        try {
            magasinDAO.updateMagasin(idMagasin, magasin);
            request.setAttribute("magasins", magasinDAO.getMagasins());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/magasin/magasins.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String strIdMagasin = request.getParameter("idMagasin");

        try {
            // Ajout du magasin dans l'attribut.
            request.setAttribute("magasin", magasinDAO.getMagasinById(Integer.parseInt(strIdMagasin)));
            request.getRequestDispatcher("views/magasin/update_magasin.jsp").forward(request, response);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
