import Model.Gebruiker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Sander on 9-9-2016.
 */
@WebServlet("/HuurderServlet")
public class HuurderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Gebruiker ingelogteGebruiker = (Gebruiker) session.getAttribute("ingelogteGebruiker");

        //alleen ingelogte gebruikers mogen hier komen
        if (ingelogteGebruiker == null) {
            //zorg dat we kunnen schrijven naar de gebruiker
            PrintWriter out = response.getWriter();
            out.print("<!doctype html\">\n"
                    + "<html>\n"
                    + "<head><title>Beheer</title></head>\n"
                    + "<body>"
            );
            out.print("<h1>Access Denied</h1>"
                    + "</body></html>");
            return;
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/huurder.html");
        dispatcher.forward(request, response);
    }
}
