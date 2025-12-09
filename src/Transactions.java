import java.time.LocalDate;
import java.util.ArrayList;

public class Transactions {
    private int id;
    private int user_id;
    private LocalDate date;
    private String ticker;
    private Float price;
    private String currency;
    private String order_type;
    private int quantity;
    //private Salg salg;//

    ArrayList<Aktie> Aktier = new ArrayList<>();

    public Transactions() {
    }

    public Transactions(int id, int user_id, LocalDate date, String ticker, float price, String currency, String order_type, int quantity) {

        this.id = id;
        this.user_id = user_id;
        this.ticker = ticker;
        this.price = price;
        this.currency = currency;
        this.date = date;
        this.order_type = order_type;
        this.quantity = quantity;
    }

    public String toString() {
        return "Transaction ID: " + id + ", User ID: " + user_id + " Date: " + date + ", Ticker: " + ticker + ", Price: " + price + ", currency: " + currency + ", order_type: " + order_type + ", Quantity: " + quantity;
    }



    public int getId(){
        return id;
    }

    public int getUserid(){
        return user_id;
    }

    public String getTicker(){
        return ticker;
    }

    public float getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public String getCurrency(){
        return currency;
    }

    public LocalDate getDato(){
        return date;
    }

    public String getOrder(){
        return order_type;
    }

    //public Salg getSalg(){
        //return salg;
    }

