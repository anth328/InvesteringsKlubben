import java.util.ArrayList;

public class Transactions {
    private int id;
    private int userid;
    private String ticker;
    private Float pris;
    private int mængde;
    private float valuta;
    private int dato;
    private int order;
    private Salg salg;

    ArrayList<Aktie> Aktier = new ArrayList<>();

    public Transactions(){}

    public Transactions(int id, int userid, String ticker, float pris, int mængde, float valuta, int dato, int order, Salg salg){
        this.id = id;
        this.userid = userid;
        this.ticker = ticker;
        this.pris = pris;
        this.mængde = mængde;
        this.valuta = valuta;
        this.dato = dato;
        this.order = order;
        this.salg = salg;
    }
}
