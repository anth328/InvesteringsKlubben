import java.time.LocalDate;

public class Aktie {
    private String ticker;
    private String navn;
    private String sektor;
    private float pris;
    private Valuta valuta;
    private String rating;
    private float dividendYield;
    private String market;
    private LocalDate lastUpdated;

    Aktie(){}

    public Aktie(String ticker, String navn, String sektor, float pris, Valuta valuta, String rating, float dividendYield, String market,LocalDate lastUpdated){
        this.ticker = ticker;
        this.navn = navn;
        this.sektor = sektor;
        this.pris = pris;
        this.valuta = valuta;
        this.rating = rating;
        this.dividendYield = dividendYield;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }


    @Override

    public String toString() {
        return "ticker: " + ticker + " Navn: " + navn + " Sektor: " + sektor + " Pris: " + pris + " Valuta: " + valuta + " Rating: " + rating +
                " DividendYield: " + dividendYield + " Market: " + market + "Last Updated: " + lastUpdated;
    }
}
