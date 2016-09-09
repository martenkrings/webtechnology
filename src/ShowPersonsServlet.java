import Model.Gebruiker;
import Model.Huurder;
import Model.Verhuurder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Geeft een lijst van huurders en verhuurders indien ingelogt
 */
@WebServlet("/ShowPersonsServlet")
public class ShowPersonsServlet extends HttpServlet {
    private ArrayList<Gebruiker> gebruikers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        gebruikers = (ArrayList<Gebruiker>) getServletContext().getAttribute("gebruikers");

        HttpSession session = request.getSession();
        Gebruiker ingelogteGebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.print("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Beheer</title></head>\n"
                + "<body>"
        );

        //alleen ingelogte gebruikers mogen hier komen
        if (ingelogteGebruiker == null){
            out.print("<h1>Access Denied</h1>"
                    + "</body></html>");
            return;
        }

        //start de list in de html
        out.print("<h1>Huurders</h1>");

        //print de gebruiker informatie
        for (Gebruiker gebruiker : gebruikers) {
            if (gebruiker instanceof Verhuurder) {
                out.print("Verhuurder: <br>" +
                        "Gebruikersnaam: " + gebruiker.getGebruikersnaam() + "<br><br>");
            } else {
                out.print("Huurder: <br>"
                        + "Gebruikersnaam: " + gebruiker.getGebruikersnaam() + "<br><br>");
            }
        }
        out.print("</ul><br>");

        out.print("</body></head></html>");
    }
}
