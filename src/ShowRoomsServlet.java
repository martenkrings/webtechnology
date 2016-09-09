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

/**
 * Toont alle kamers van een verhuurder
 */
@WebServlet("/ShowRoomsServlet")
public class ShowRoomsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //haal de gebruiker op
        HttpSession session = request.getSession();
        Gebruiker gebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //zorg dat we kunnen schrijven naar de gebruiker
        PrintWriter out = response.getWriter();
        out.println("<!doctype html\">\n"
                + "<html>\n"
                + "<head><title>Acces Denied</title></head>\n"
                + "<body>");

        //alleen verhuurders hebben toegang tot deze pagina
        if (gebruiker instanceof Huurder){
            out.print("<h1>Acces Denied</h1></body></html>");
            return;
        }
    }
}
