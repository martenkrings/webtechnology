import Model.Gebruiker;
import Model.Huurder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sander on 5-9-2016.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    ArrayList<Gebruiker> gebruikers;
    Gebruiker ingelogteGebruiker;

    @Override
    public void init() throws ServletException {
        super.init();
        ingelogteGebruiker = null;
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gebruikers = (ArrayList<Gebruiker>) getServletContext().getAttribute("gebruikers");
        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = request.getParameter("wachtwoord");


        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker.getGebruikersnaam().equals(gebruikersnaam)) {
                ingelogteGebruiker = gebruiker;
            }
        }

        //als het wachtwoord niet overeen komt maak gebruiker weer null
        if (ingelogteGebruiker != null && !ingelogteGebruiker.getWachtwoord().equals(wachtwoord)) {
            ingelogteGebruiker = null;
        }

        RequestDispatcher dispatcher;

        //als gebruiker null is dan is er een foute login poging
        if (ingelogteGebruiker == null) {
            response.sendRedirect("/fouteinlog.html");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("ingelogteGebruiker", ingelogteGebruiker);
            if (ingelogteGebruiker instanceof Huurder) {
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/huurderIndex.html");
                dispatcher.forward(request, response);
            } else {
                dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/verhuurderIndex.html");
                dispatcher.forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("ingelogteGebruiker");
        response.sendRedirect("/index.html");
    }
}
