import java.lang.reflect.Array;
import java.util.ArrayList;

public class Portfolio {

    private float balance;
    private int maengde;
    private String ticker;

    private ArrayList<Aktie> egetAktier =  new ArrayList<>();



    public Portfolio(float balance, int maengde, String ticker){

        this.balance = balance;
        this.maengde = maengde;
        this.ticker = ticker;

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
    public ArrayList<Aktie> getEgneAktier() {
        return egetAktier;
    }
    public void addAktier(Aktie aktier) {
        egetAktier.add(aktier);
    }
public void removeAktier(Aktie aktier) {
        egetAktier.remove(aktier);
}


@Override

    public String toString (){
        return "Balance: "+balance+ " Mængde: "+maengde+ " Ticker: "+ticker;
    }


}
