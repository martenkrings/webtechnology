import Model.Huurder;
import Model.Verhuurder;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;

/**
 * Created by Gebruiker on 9/5/2016.
 */
@WebListener()
public class ServletListener implements ServletContextListener {

    private ArrayList<Huurder> huurders;
    private ArrayList<Verhuurder> verhuurders;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        huurders = new ArrayList<>();
        verhuurders = new ArrayList<>();
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
