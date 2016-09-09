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

        PrintWriter out = response.getWriter();
        out.print("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Beheer</title></head>\n"
                + "<body>\n"
                + "<h1>Huurders</h1>"
                + "<ul style=\"list-style-type:none\">");

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
