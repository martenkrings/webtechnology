package Model;

/**
 *
 */
public class Kamer {
    private String naam;
    private Verhuurder verhuurder;
    private String plaats;
    private double oppervlakte;
    private int aantalPersonen;
    private double prijs;

    public Kamer(String naam, Verhuurder verhuurder, String plaats, double oppervlakte, int aantalPersonen, double prijs) {
        this.naam = naam;
        this.verhuurder = verhuurder;
        this.plaats = plaats;
        this.oppervlakte = oppervlakte;
        this.aantalPersonen = aantalPersonen;
        this.prijs = prijs;
    }

    public String getPlaats() {
        return plaats;
    }

    public String getNaam() {
        return naam;
    }

    public double getOppervlakte() {
        return oppervlakte;
    }

    public int getAantalPersonen() {
        return aantalPersonen;
    }

    public double getPrijs() {
        return prijs;
    }

    public Verhuurder getVerhuurder() {
        return verhuurder;
    }
}
