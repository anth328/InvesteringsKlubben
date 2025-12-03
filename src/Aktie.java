import java.time.LocalDate;

public class Aktie {
    private String ticker;
    private String name;
    private String sector;
    private float price;
    private String currency;
    private String rating;
    private float dividendYield;
    private String market;
    private LocalDate lastUpdated;

    Aktie(){}

    public Aktie(String ticker, String name, String sector, float price, String currency, String rating, float dividendYield, String market,LocalDate lastUpdated){
        this.ticker = ticker;
        this.name = name;
        this.sector = sector;
        this.price = price;
        this.currency = currency;
        this.rating = rating;
        this.dividendYield = dividendYield;
        this.market = market;
        this.lastUpdated = lastUpdated;
    }


    public float getPrice(){
        return price;
    }


    @Override

    public String toString() {
        return "ticker: " + ticker + " Navn: " + name + " Sektor: " + sector + " Pris: " + price + " Valuta: " + currency + " Rating: " + rating +
                " DividendYield: " + dividendYield + " Market: " + market + "Last Updated: " + lastUpdated;
    }
}
