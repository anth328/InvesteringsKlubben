import java.lang.reflect.Array;
import java.util.ArrayList;

public class Portfolio {

    private float balance;
    private int maengde;
    private String ticker;
    private float buyPrice;
    private float currentPrice;

    private ArrayList<Aktie> egetAktier;


    public Portfolio(float balance, int maengde, String ticker, float buyPrice, float currentPrice){

        this.balance = balance;
        this.maengde = maengde;
        this.ticker = ticker;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
        egetAktier =  new ArrayList<>();

    }
    public float getBalance () {
        return balance;
    }

    public int getMaengde(){
        return maengde;
    }

    private String getTicker(){
        return ticker;
    }

    public float getBuyPrice(){
        return buyPrice;
    }
    public float getCurrentPrice(){
        return currentPrice;
    }


    public ArrayList<Aktie> getEgneAktier() {
        return egetAktier;
    }
    public void addAktier(Aktie aktier) {
        egetAktier.add(aktier);
    }
    public void removeAktier(Aktie aktier) {
        egetAktier.remove(aktier);
    }

    public float getAktiePrice(){
        if (egetAktier.isEmpty()) {
            return 0;
        }
        return egetAktier.get(0).getPrice();
    }

    public float calculateProfitLoss() {
        if (egetAktier.isEmpty()) {
            return 0;
        }
        float aktiePrice = egetAktier.get(0).getPrice();
        return (maengde * aktiePrice) - (maengde * buyPrice);
    }




    @Override

    public String toString (){
        return "Balance: "+balance+ " Mængde: "+maengde+ " Ticker: "+ticker+ "Aktier eget: "+egetAktier+ "Profit/Loss: "+ calculateProfitLoss();

    }


}