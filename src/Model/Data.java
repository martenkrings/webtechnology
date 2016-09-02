package Model;

import java.util.ArrayList;

/**
 * Created by Model.Gebruiker on 9/1/2016.
 */
public class Data {

    public ArrayList<Huurder> huurders;
    public ArrayList<Verhuurder> verhuurders;

    private static Data ourInstance = new Data();

    public static Data getInstance() {
        return ourInstance;
    }

    private Data() {
        huurders = new ArrayList<>();
        verhuurders = new ArrayList<>();
    }

}
