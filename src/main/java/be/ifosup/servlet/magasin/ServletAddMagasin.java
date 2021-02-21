package be.ifosup.servlet.magasin;

import be.ifosup.dao.DAOFactory;
import be.ifosup.magasin.Magasin;
import be.ifosup.magasin.MagasinDAO;
import be.ifosup.utils.FormsConstantes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAddMagasin", urlPatterns = "/add_magasin")
public class ServletAddMagasin extends HttpServlet {
    private MagasinDAO magasinDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.magasinDAO = daoFactory.getMagasinDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Recuperation de la mesure dans le formulaire.
        String nomMagasin = request.getParameter("nomMagasin");
        // Si les champs sont nulles on ajoute un message d'erreur et on redirige.
        if (nomMagasin.isEmpty()) {

            try {
                // Redirection.
                String error = FormsConstantes.EMPTY_STRINGS;
                request.setAttribute("error", error);
                request.setAttribute("magasins", magasinDAO.getMagasins());
                request.getRequestDispatcher("views/magasin/magasins.jsp").forward(request, response);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        } else {

            try {
                Magasin magasin = new Magasin();
                magasin.setNom(nomMagasin);
                // Enregistrement du magasin dans la db.
                magasinDAO.createMagasin(magasin);
                // Redirection.
                String success = FormsConstantes.SUCCESS_NEW_MAGASIN;
                request.setAttribute("success", success);
                request.setAttribute("magasins", magasinDAO.getMagasins());
                request.getRequestDispatcher("views/magasin/magasins.jsp").forward(request, response);
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
    }

}
