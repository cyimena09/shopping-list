package be.ifosup.shopping.servlet.mesure;

import be.ifosup.shopping.mesure.MesureDAO;
import be.ifosup.shopping.daoFactory.DAOFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ServletDeleteMesure", urlPatterns = "/delete_mesure")
public class ServletDeleteMesure extends HttpServlet {
    private MesureDAO mesureDAO;

    public void init() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        this.mesureDAO = daoFactory.getMesureDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupération de l'id de le mesure à supprimer.
        String idMesure = request.getParameter("idMesure");

        try {
            // On appelle la méthode delete.
            mesureDAO.deleteMesure(Integer.parseInt(idMesure));
            request.setAttribute("mesures", mesureDAO.getMesures());
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        request.getRequestDispatcher("views/mesure/mesures.jsp").forward(request, response);
    }

}
