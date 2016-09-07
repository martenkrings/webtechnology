import Model.Gebruiker;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Sander on 5-9-2016.
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String gebruikersnaam = request.getParameter("gebruikersnaam");
        System.out.println(gebruikersnaam);
        String wachtwoord = request.getParameter("wachtwoord");
        System.out.println(wachtwoord);
        Boolean ingelogd = false;
        Gebruiker gebruiker;
        gebruiker = (Gebruiker)getServletContext().getAttribute(gebruikersnaam);
        System.out.println(gebruiker);

        //als het wachtwoord niet overeen komt maak gebruiker weer null
        if (gebruiker != null && gebruiker.getWachtwoord() != wachtwoord){
            gebruiker = null;
        }

        //als gebruiker null is dan is er een foute login poging
        if (gebruiker == null){
            response.sendRedirect("/fouteinlog.html");
        } else {

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
