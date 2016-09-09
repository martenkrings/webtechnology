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
 * Created by Gebruiker on 2016-09-09.
 */
@WebServlet("/AddRoomServlet")
public class AddRoomServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("I'm gonna Post!!!!!!!");
        //haal de gebruiker op
        HttpSession session = request.getSession();
        Verhuurder verhuurder = (Verhuurder) session.getAttribute("ingelogteGebruiker");

        ArrayList<Kamer> kamers = (ArrayList<Kamer>) getServletContext().getAttribute("kamers");

        if (!request.getParameter("naam").isEmpty() && !request.getParameter("plaats").isEmpty() &&
                !request.getParameter("oppervlakte").isEmpty() && !request.getParameter("aantalPersonen").isEmpty() &&
                !request.getParameter("prijs").isEmpty() &&
                (Double.parseDouble(request.getParameter("oppervlakte")) > 0) &&
                (Double.parseDouble(request.getParameter("prijs")) > 0)) {

            //maak nieuwe kamer
            String naam = request.getParameter("naam");
            String plaats = request.getParameter("plaats");
            Double oppervlakte = Double.parseDouble(request.getParameter("oppervlakte"));
            int personen = Integer.parseInt(request.getParameter("aantalPersonen"));
            Double prijs = Double.parseDouble(request.getParameter("prijs"));

            Kamer k = new Kamer(naam, verhuurder, plaats, oppervlakte, personen, prijs);
            kamers.add(k);

            //vervang data
            getServletContext().removeAttribute("kamers");
            getServletContext().setAttribute("kamers", kamers);

            //forward naar ShowRoomsServlet
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/ShowRoomsServlet");
            dispatcher.forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.println("<!doctype html\">\n"
                    + "<html>\n"
                    + "<head><title>Foute Invoer</title></head>\n"
                    + "<body>"
                    + "<h1>Foute Invoer</h1></body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //haal de gebruiker op
        HttpSession session = request.getSession();
        Gebruiker gebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //alleen verhuurders hebben toegang tot deze pagina
        if (gebruiker == null || gebruiker instanceof Huurder) {
            PrintWriter out = response.getWriter();
            out.println("<!doctype html\">\n"
                    + "<html>\n"
                    + "<head><title>Mijn Kamers</title></head>\n"
                    + "<body>"
                    + "<h1>Acces Denied</h1></body></html>");
        } else {
            //forward naar addroom.html
            RequestDispatcher dispatcher;
            dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/addroom.html");
            dispatcher.forward(request, response);
        }

    }
}
