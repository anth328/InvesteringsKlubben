import java.io.*;
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

    public Transactions() {
    }

    public Transactions(int id, int userid, String ticker, float pris, int maengde, float valuta, int dato, int order, Salg salg) {
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


    public int getId(){
        return id;
    }

    public int getUserid(){
        return userid;
    }

    public String getTicker(){
        return ticker;
    }

    public float getPris(){
        return pris;
    }

    public int getMaengde(){
        return maengde;
    }

    public float getValuta(){
        return valuta;
    }

    public int getDato(){
        return dato;
    }

    public int getOrder(){
        return order;
    }

    public Salg getSalg(){
        return salg;
    }

}
