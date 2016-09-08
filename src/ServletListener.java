import Model.Huurder;
import Model.Kamer;
import Model.Verhuurder;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 9/5/2016.
 */
@WebListener()
public class ServletListener implements ServletContextListener {

    ServletContext context;
    private ArrayList<Huurder> huurders;
    private ArrayList<Verhuurder> verhuurders;
    private ArrayList<Kamer> kamers;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        huurders = new ArrayList<>();
        verhuurders = new ArrayList<>();
        kamers = new ArrayList<>();

        //dummy data
        Huurder testHuurder = new Huurder("Sander", "wachtwoord");
        Verhuurder testVerhuurder = new Verhuurder("Marten", "wachtwoord");
        Kamer testkamer = new Kamer("Kamer met uitzicht op deventer kerk",testVerhuurder, "Deventer", 15, 2, 450);
        testVerhuurder.addKamer(testkamer);

        huurders.add(testHuurder);
        verhuurders.add(testVerhuurder);
        kamers.add(testkamer);

        //dummy data toevoegen
        context = servletContextEvent.getServletContext();
        context.setAttribute("Sander", testHuurder);
        context.setAttribute("Marten", testVerhuurder);
        context.setAttribute("huurders", huurders);
        context.setAttribute("verhuurders", verhuurders);
        context.setAttribute("kamers", kamers);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
