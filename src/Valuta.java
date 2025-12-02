import java.time.LocalDate;

public class Valuta {

    private String currency;
    private String quoteCurrency;
    private float rate;
    private LocalDate lastUpdate;


    public Valuta(String currency, String quoteCurrency, float rate, LocalDate lastUpdate){

        this.currency = currency;
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
        this.lastUpdate = lastUpdate;

    }

    @Override
    public String toString(){
        return "Valuta: " + currency + " citatValuta: " + quoteCurrency + " Rate: " + rate + " Sidst Opdateret: " + lastUpdate;
    }
}
