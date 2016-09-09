import Model.Gebruiker;
import Model.Huurder;
import Model.Verhuurder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

        Cookie timesBeenHereCookie = null;
        Cookie lastDateBeenHereCookie = null;

        Cookie[] cookieArray = request.getCookies();
        for (Cookie cookie:cookieArray){
            if (cookie.getName().equals("timesBeenHere")){
                timesBeenHereCookie = cookie;
            }
            if (cookie.getName().equals("lastDateBeenHere")){
                lastDateBeenHereCookie = cookie;
            }
        }

        //store cookie data or fill with 0/nvt
        String timesHereString = null;
        if (timesBeenHereCookie != null) {
             timesHereString = timesBeenHereCookie.getValue();
        } else {
            timesHereString = 0 + "";
        }
        String dateHereString = null;
        if (lastDateBeenHereCookie != null){
            dateHereString = lastDateBeenHereCookie.getValue();
        } else {
            dateHereString = "NVT";
        }

        //print de cookie data
        out.print("<h1>Times been here: " + timesHereString + "<br>"
                + "Last date been here: " + dateHereString + "</h1>");

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

        //verhoog de timesBeenHereCookie
        if (timesBeenHereCookie == null) {
            timesBeenHereCookie = new Cookie("timesBeenHere", "1");
        } else {
            int counter = Integer.parseInt(timesBeenHereCookie.getValue());
            counter++;
            timesBeenHereCookie.setValue(counter + "");
        }
        response.addCookie(timesBeenHereCookie);

        //make new date object
        Date date = new Date();
        String dateString = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date);

        //replace old date with new date
        lastDateBeenHereCookie = new Cookie("lastDateBeenHere", dateString + "");
        response.addCookie(lastDateBeenHereCookie);

        out.print("</ul><br>");

        out.print("</body></head></html>");
    }
}
