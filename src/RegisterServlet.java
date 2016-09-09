import Model.Gebruiker;
import Model.Huurder;
import Model.Verhuurder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by Sander on 9-9-2016.
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private ArrayList<Gebruiker> gebruikers;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gebruikers = (ArrayList<Gebruiker>) getServletContext().getAttribute("gebruikers");

        String gebruikersnaam = request.getParameter("gebruikersnaam");
        String wachtwoord = request.getParameter("wachtwoord");
        String type = request.getParameter("functie");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.print("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Registratie</title></head>\n"
                + "<body>"
        );

        //niet alle velden gevuld
        if (gebruikersnaam == null || wachtwoord == null || type == null){
            out.print("<h1>Niet alle velden ingevuld!</h1> \n"
                    + "<a style=\"font-size:200%;\" href=\"/registreer.html\">Terug?</a></body></html>");
            return;
        }



        //kijk of gebruikersnaam niet al in gebruik is
        for (Gebruiker gebruiker:gebruikers){
            if (gebruiker.getGebruikersnaam().equals(gebruikersnaam)){
                out.print("<h1>Gebruikersnaam in gebruik!</h1> \n " +
                        "<a style=\"font-size:200%;\" href=\"/registreer.html\">Terug?</a></body></html>");
                return;
            }
        }

        if (type.equals("Huurder")){
            Huurder huurder = new Huurder(gebruikersnaam, wachtwoord);
            gebruikers.add(huurder);
        } else {
            Verhuurder verhuurder = new Verhuurder(gebruikersnaam, wachtwoord);
            gebruikers.add(verhuurder);
        }

        //vervang data
        getServletContext().removeAttribute("gebruikers");
        getServletContext().setAttribute("gebruikers", gebruikers);

        //eindig html
        out.print("</body></html>");
        response.sendRedirect("/login.html");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
