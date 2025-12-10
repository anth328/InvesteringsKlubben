import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Aktie {
    private String ticker;
    private String name;
    private String sector;
    private float price;
    private Currency currency;
    private String rating;
    private float dividendYield;
    private String market;
    private LocalDate lastUpdated;

    Aktie() {
    }

    public Aktie(String ticker, String name, String sector, float price, Currency currency, String rating, float dividendYield, String market, LocalDate lastUpdated) {
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

    public String getTicker() {
        return ticker;
    }

    public Currency getCurrency(){
        return currency;
    }

    public float getPrice() {
        return price;
    }

    public void printBasic() {
        System.out.println("Navn: " + name + " | Pris: " + price);
    }

    @Override

    public String toString() {
        return "ticker: " + ticker + " Navn: " + name + " Sektor: " + sector + " Pris: " + price + " Valuta: " + currency + " Rating: " + rating +
                " DividendYield: " + dividendYield + " Market: " + market + "Last Updated: " + lastUpdated;
    }
}
