import Model.Gebruiker;
import Model.Huurder;
import Model.Kamer;
import Model.Verhuurder;

import javax.servlet.RequestDispatcher;
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
 * Toont alle kamers van een verhuurder
 */
@WebServlet("/ShowRoomsServlet")
public class ShowRoomsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Kamer> kamers;

        //haal de gebruiker op
        HttpSession session = request.getSession();
        Gebruiker gebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.println("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Mijn Kamers</title></head>\n"
                + "<body>");

        //alleen verhuurders hebben toegang tot deze pagina
        if (gebruiker == null || gebruiker instanceof Huurder) {
            out.print("<h1>Acces Denied</h1></body></html>");
            return;
        }

        out.print("<a style=\"font-size:200%;\" href=\"/AddRoomServlet\">addroom</a> <br><br>");
        kamers = new ArrayList<>();
        for (Kamer k : (ArrayList<Kamer>) getServletContext().getAttribute("kamers")) {
            if (k.getVerhuurder().equals(gebruiker)) {
                kamers.add(k);
            }
        }

        for (Kamer kamer : kamers) {
            out.print(kamer.getNaam() + "<br>"
                    + "Plaats: " + kamer.getPlaats() + "<br>"
                    + "Personen: " + kamer.getAantalPersonen() + "<br>"
                    + "Prijs: " + kamer.getPrijs() + "<br>"
                    + "Oppervlakte: " + kamer.getOppervlakte() + "<br><br>");
        }

        //eindig html
        out.print("</body></html>");

    }
}
