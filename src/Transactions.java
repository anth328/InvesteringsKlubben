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

    public void saveTransactionToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.csv", true))) {
            writer.write(id + ";" + userid + ";" + ticker + ";" + pris + ";" +
                    maengde + ";" + valuta + ";" + dato + ";" + order + ";" + salg);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Fejl i skrivning til fil: " + e.getMessage());
        }
    }

    /*
    Oprette en ArrayList og initierere den
    Lave en add metode til at tilføje en transaktion til ArrayListen
    en metode til at læse transaktioner fra en fil kunne se sådan ud
     */
    public void readTransactionsFromFile() {
    try (BufferedReader br = new BufferedReader(new FileReader("transactions.csv"))) {
        String line;
        while ((line = br.readLine()) != null) {
            
        }

    } catch (Exception e) {
        System.out.println("Fejl ved læsning af fil: " + e.getMessage());
    }
    }


}




