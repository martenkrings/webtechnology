package Model;

import java.util.ArrayList;

/**
 * Created by Gebruiker on 9/1/2016.
 */
public class Verhuurder extends Gebruiker {
    private ArrayList<Kamer> kamers;

    public Verhuurder(String gebruikersnaam, String wachtwoord) {
        super(gebruikersnaam, wachtwoord);
        kamers = new ArrayList<>();
    }

    public void addKamer(Kamer kamer) {
        kamers.add(kamer);
    }

    public ArrayList<Kamer> getKamers() {
        return kamers;
    }


}
