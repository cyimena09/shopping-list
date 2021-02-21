package be.ifosup.servlet.mesure;

import be.ifosup.mesure.Mesure;
import be.ifosup.mesure.MesureDAO;
import be.ifosup.dao.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletAddMesure", urlPatterns = "/add_mesure")
public class ServletAddMesure extends HttpServlet {
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Force UTF-8.
        request.setCharacterEncoding("UTF-8");
        // Recuperation de la mesure dans le formulaire.
        String nomMesure = request.getParameter("nomMesure");

        try {
            // Ajout de la mesure dans la db.
            mesureDAO.createMesure(new Mesure(null, nomMesure));
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/mesure/mesures.jsp").forward(request, response);
    }

}
