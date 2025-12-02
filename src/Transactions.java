import java.util.ArrayList;

public class Transactions {
    private int id;
    private int userid;
    private String ticker;
    private Float pris;
    private int maengde;
    private float valuta;
    private int dato;
    private int order;
    private Salg salg;

    ArrayList<Aktie> Aktier = new ArrayList<>();

    public Transactions(){}

    public Transactions(int id, int userid, String ticker, float pris, int maengde, float valuta, int dato, int order, Salg salg){
        this.id = id;
        this.userid = userid;
        this.ticker = ticker;
        this.pris = pris;
        this.maengde = maengde;
        this.valuta = valuta;
        this.dato = dato;
        this.order = order;
        this.salg = salg;
    }

    public String toString() {
        return "Transaction ID: " + id + ", User ID: " + userid + ", Ticker: " + ticker + ", Pris: " + pris + ", Mængde: " + maengde + ", Valuta: " + valuta + ", Dato: " + dato + ", Ordre: " + order + ", Salg Info: " + salg;
    }

    }

