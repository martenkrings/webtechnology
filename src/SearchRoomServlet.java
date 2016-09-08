import Model.Kamer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Toont kamers die voldoen aan het zoek criteria
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
    private ArrayList<Kamer> kamers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //de zoek criteria
        int minVierkanteMeters = Integer.parseInt(request.getParameter("zoekMinVierkanteMeters"));
        int maxVierkanteMeters = Integer.parseInt(request.getParameter("zoekMaxVierkanteMeters"));
        String plaats = (String) request.getParameter("zoekPlaats");
        int minPrijs = Integer.parseInt(request.getParameter("zoekMinPrijs"));
        int maxPrijs = Integer.parseInt(request.getParameter("zoekMaxPrijs"));
        int aantalPersonen = Integer.parseInt(request.getParameter("zoekPersonen"));

        //haal alle kamers op
        kamers = (ArrayList<Kamer>) request.getServletContext().getAttribute("kamers");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.print("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Search Results</title></head>\n"
                + "<body>"
        );

        int counter = 0;

        //kijk welke kamers er aan het zoek criteria voldoen
        for (Kamer kamer : kamers) {
            if (kamer.getAantalPersonen() != aantalPersonen || kamer.getOppervlakte() < minVierkanteMeters || kamer.getOppervlakte() > maxVierkanteMeters || kamer.getPrijs() < minPrijs || kamer.getPrijs() > maxPrijs || kamer.getAantalPersonen() != aantalPersonen || !kamer.getPlaats().equalsIgnoreCase(plaats)) {
                //do nothing
            } else {
                counter++;
                //schrijf de kamer naar de gebruiker
                out.print("<h" + counter + ">" + kamer.getNaam() + "</h" + counter + "> \n"
                        + "<p" + counter + ">" + "Verhuurder: " + kamer.getVerhuurder().getGebruikersnaam() + "<br>"
                        + "Prijs: &#8364;" + kamer.getPrijs() + "<br>"
                        + "Oppervlakte: " + kamer.getOppervlakte() + "m²<br>"
                        + "personen: " + kamer.getAantalPersonen() + "<br>"
                        + "plaats: " + kamer.getPlaats() + "<br><br>");
            }
        }

        //als er geen zoek resultaten zijn print dat dan naar de gebruiker
        if (counter == 0) {
            out.print("<h1>Helaas! geen resultaten gevonden </h1>");
        }
        //eindig de html
        out.print("</body></html>");
    }
}
