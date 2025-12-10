import java.time.LocalDate;
import java.util.Objects;

public class Currency {

    private String currency;
    private String quoteCurrency;
    private float rate;
    private LocalDate lastUpdate;

    public Currency(){}

    public Currency(String currency, String quoteCurrency, float rate, LocalDate lastUpdate){

        this.currency = currency;
        this.quoteCurrency = quoteCurrency;
        this.rate = rate;
        this.lastUpdate = lastUpdate;

    }

    public String getCurrency(){
        return currency;
    }

    public String getQuoteCurrency(){
        return quoteCurrency;
    }

    public float getRate(){
        return rate;
    }

    public LocalDate getLastUpdated(){
        return lastUpdate;
    }

    public float calculateCurrencyToDKK(Currency currency,Transactions transaction){
        float money = transaction.getPrice();
        if(Objects.equals(transaction.getCurrency(), currency.getCurrency())){
            money = money * currency.getRate();
        }
        return money;
    }

    @Override
    public String toString(){
        return "Valuta: " + currency + " Rate: " + rate + " Sidst Opdateret: " + lastUpdate;
    }
}
