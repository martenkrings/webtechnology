package Model;

/**
 * Created by Model.Gebruiker on 9/1/2016.
 */
public class Gebruiker {
    private String gebruikersnaam;
    private String wachtwoord;

    public Gebruiker(String gebruikersnaam, String wachtwoord) {
        this.gebruikersnaam = gebruikersnaam;
        this.wachtwoord = wachtwoord;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }
}
