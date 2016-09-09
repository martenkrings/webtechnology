import Model.Gebruiker;
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
    ArrayList<Gebruiker> gebruikers;
    ServletContext context;
    private ArrayList<Kamer> kamers;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        gebruikers = new ArrayList<>();
        kamers = new ArrayList<>();

        //voeg data toe aan context
        context = servletContextEvent.getServletContext();
        context.setAttribute("gebruikers", gebruikers);
        context.setAttribute("kamers", kamers);

        //voeg dummyData toe
        dummyData();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

    private void dummyData() {
        //dummy data
        Huurder testHuurder = new Huurder("Sander", "wachtwoord");
        Huurder testHuurder2 = new Huurder("Hans", "wacthwoord");
        Huurder testHuurder3 = new Huurder("Felix", "pasword");
        Verhuurder testVerhuurder = new Verhuurder("Marten", "wachtwoord");
        Verhuurder testVerhuurder2 = new Verhuurder("Sjon", "wachtwoord");
        Verhuurder testVerhuurder3 = new Verhuurder("Henk", "wachtwoord");
        Kamer testKamer = new Kamer("Kamer met uitzicht op deventer kerk", testVerhuurder, "Deventer", 15, 2, 450);
        Kamer testKamer2 = new Kamer("Kamer in de binnenstad", testVerhuurder, "Deventer", 24, 2, 575);
        Kamer testKamer3 = new Kamer("Kamer die je niet wil hebben", testVerhuurder2, "Enschede", 5, 4, 1000);
        testVerhuurder.addKamer(testKamer);
        testVerhuurder.addKamer(testKamer2);
        testVerhuurder2.addKamer(testKamer3);

        gebruikers.add(testHuurder);
        gebruikers.add(testHuurder2);
        gebruikers.add(testHuurder3);
        gebruikers.add(testVerhuurder);
        gebruikers.add(testVerhuurder2);
        gebruikers.add(testVerhuurder3);
        kamers.add(testKamer);
        kamers.add(testKamer2);
        kamers.add(testKamer3);
    }
}
