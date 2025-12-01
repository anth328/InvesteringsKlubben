import java.time.LocalDate;

public class Valuta {

    private String currency;
    private String qouteCurrency;
    private float rate;
    private LocalDate lastUpdate;


    public Valuta(String currency, String qouteCurrency, float rate, LocalDate lastUpdate){

        this.currency = currency;
        this.qouteCurrency = qouteCurrency;
        this.rate = rate;
        this.lastUpdate = lastUpdate;

    }
}
