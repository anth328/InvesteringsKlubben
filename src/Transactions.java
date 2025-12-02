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

    public void saveTransactionToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(id + ";" + userid + ";" + ticker + ";" + pris + ";" +
                    maengde + ";" + valuta + ";" + dato + ";" + order + ";" + salg);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Fejl i skrivning til fil: " + e.getMessage());
        }
    }


    public void loadTransactionsFromFile(String filename) {
        ArrayList<Transactions> transactionsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 9) {
                    int id = Integer.parseInt(parts[0]);
                    int userid = Integer.parseInt(parts[1]);
                    String ticker = parts[2];
                    float pris = Float.parseFloat(parts[3]);
                    int maengde = Integer.parseInt(parts[4]);
                    float valuta = Float.parseFloat(parts[5]);
                    int dato = Integer.parseInt(parts[6]);
                    int order = Integer.parseInt(parts[7]);
                    Salg salg = Salg.valueOf(parts[8]);

                    Transactions transaction = new Transactions(id, userid, ticker, pris, maengde, valuta, dato, order, salg);
                    transactionsList.add(transaction);
                }
            }
        } catch (IOException e) {
            System.out.println("Fejl i læsning fra fil: " + e.getMessage());
        }


    }




}