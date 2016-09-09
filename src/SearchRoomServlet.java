import Model.Gebruiker;
import Model.Kamer;

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
 * Toont kamers die voldoen aan het zoek criteria
 */
@WebServlet("/SearchRoomServlet")
public class SearchRoomServlet extends HttpServlet {
    private ArrayList<Kamer> kamers;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //haal de gebruiker op
        HttpSession session = request.getSession();
        Gebruiker ingelogteGebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.print("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Search Results</title></head>\n"
                + "<body>"
        );

        //alleen ingelogte gebruikers mogen hier komen
        if (ingelogteGebruiker == null) {
            out.print("<h1>Access Denied</h1>"
                    + "</body></html>");
            return;
        }

        //geen lege velden
        if (request.getParameter("zoekMinVierkanteMeters") == "" || request.getParameter("zoekMaxVierkanteMeters") == "" || request.getParameter("zoekMinPrijs") == "" || request.getParameter("zoekMaxPrijs") == "" || request.getParameter("zoekPlaats") == ""){
            out.print("<h1>Niet alle velden gevuld!</h1>"
                    + "</body></html>");
            return;
        }

        //geen negatiefe velden of max > min
        if (Integer.parseInt(request.getParameter("zoekMinVierkanteMeters")) < 0 || Integer.parseInt(request.getParameter("zoekMinPrijs")) < 0 || Integer.parseInt(request.getParameter("zoekMinVierkanteMeters")) > Integer.parseInt(request.getParameter("zoekMaxVierkanteMeters"))|| Integer.parseInt(request.getParameter("zoekMinPrijs")) > Integer.parseInt(request.getParameter("zoekMaxPrijs"))){
            out.print("<h1>Foute invoer!</h1>"
                    + "</body></html>");
            return;
        }

        //de zoek criteria
        Double minVierkanteMeters = Double.parseDouble(request.getParameter("zoekMinVierkanteMeters"));
        Double maxVierkanteMeters = Double.parseDouble(request.getParameter("zoekMaxVierkanteMeters"));
        String plaats = request.getParameter("zoekPlaats");
        Double minPrijs = Double.parseDouble(request.getParameter("zoekMinPrijs"));
        Double maxPrijs = Double.parseDouble(request.getParameter("zoekMaxPrijs"));
        int aantalPersonen = Integer.parseInt(request.getParameter("zoekPersonen"));

        //haal alle kamers op
        kamers = (ArrayList<Kamer>) request.getServletContext().getAttribute("kamers");

        //counter
        int counter = 0;

        //kijk welke kamers er aan het zoek criteria voldoen
        for (Kamer kamer : kamers) {
            if (kamer.getAantalPersonen() != aantalPersonen || kamer.getOppervlakte() < minVierkanteMeters || kamer.getOppervlakte() > maxVierkanteMeters || kamer.getPrijs() < minPrijs || kamer.getPrijs() > maxPrijs || kamer.getAantalPersonen() != aantalPersonen || !kamer.getPlaats().equalsIgnoreCase(plaats)) {
                //do nothing
            } else {
                counter++;
                //schrijf de kamer naar de gebruiker
                out.print("<h1>" + kamer.getNaam() + "</h1> \n"
                        + "<p1>" + "Verhuurder: " + kamer.getVerhuurder().getGebruikersnaam() + "<br>"
                        + "Prijs: &#8364;" + kamer.getPrijs() + "<br>"
                        + "Oppervlakte: " + kamer.getOppervlakte() + "m²<br>"
                        + "personen: " + kamer.getAantalPersonen() + "<br>"
                        + "plaats: " + kamer.getPlaats() + "</p1><br><br>");
            }
        }

        //als er geen zoek resultaten zijn print dat dan naar de gebruiker
        if (counter == 0) {
            out.print("<h1>Helaas! Geen resultaten gevonden </h1>");
        }

        //eindig de html
        out.print("</body></html>");
    }
}
