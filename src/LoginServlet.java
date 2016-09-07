import Model.Gebruiker;
import Model.Huurder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Sander on 5-9-2016.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = request.getParameter("wachtwoord");
        Gebruiker gebruiker;
        gebruiker = (Gebruiker)getServletContext().getAttribute(gebruikersnaam);

        //als het wachtwoord niet overeen komt maak gebruiker weer null
        if (gebruiker != null && !gebruiker.getWachtwoord().equals(wachtwoord)){
            gebruiker = null;
        }

        //als gebruiker null is dan is er een foute login poging
        if (gebruiker == null){
            response.sendRedirect("/fouteinlog.html");
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("ingelogteGebruiker", gebruiker);
            if (gebruiker instanceof Huurder){
                response.sendRedirect("/huurderIndex.html");
            } else {
                response.sendRedirect("/verhuurderIndex.html");
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
