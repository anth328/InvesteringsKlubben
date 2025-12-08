import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class Portfolio {

    private float balance;
    private int maengde;
    private String ticker;
    private float buyPrice;
    private float currentPrice;

    private ArrayList<Aktie> egetAktier = new ArrayList<>();
    private ArrayList<Transactions> egneTransactions = new ArrayList<>();
    private DataRepository data = new DataRepository();

    public Portfolio(){};

    public Portfolio(float balance, int maengde, String ticker, float buyPrice, float currentPrice) {

        this.balance = balance;
        this.maengde = maengde;
        this.ticker = ticker;
        this.buyPrice = buyPrice;
        this.currentPrice = currentPrice;
    }

    public float getBalance() {
        return balance;
    }

    public int getMaengde() {
        return maengde;
    }

    private String getTicker() {
        return ticker;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public float getCurrentPrice() {
        return currentPrice;
    }

    public void setBalance(float balance){
        this.balance = balance;
    }

    public ArrayList<Aktie> getEgneAktier() {
        return egetAktier;
    }

    public void printEgneAktier(){
        for (Aktie a : getEgneAktier()){
            System.out.println(a);
        }
    }

    public void printEgneAktier(User user){
        for (Transactions t : egneTransactions){
            System.out.println(t);
        }
    }

    public void addAktier(Aktie aktier) {
        egetAktier.add(aktier);
    }

    public void removeAktier(Aktie aktier) {
        egetAktier.remove(aktier);
    }

    public float getAktiePrice() {
        if (egetAktier.isEmpty()) {
            return 0;
        }
        return egetAktier.get(0).getPrice();
    }

    public void calculateBalance(User user) {
        float balance = user.getInitialCash();
        for (Transactions t : egneTransactions) {
            if (t.getOrder() == "buy") {
                if (Objects.equals(t.getOrder(), "buy")) {
                    balance -= t.getPris();
                }
                if (Objects.equals(t.getOrder(), "sell")) {
                    balance += t.getPris();
                }
            }
            setBalance(balance);
        }
    }

    public float calculateProfitLoss() {
        if (egetAktier.isEmpty()) {
            return 0;
        }
        float aktiePrice = egetAktier.get(0).getPrice();
        return (maengde * aktiePrice) - (maengde * buyPrice);
    }

    public float calculateAllProfitLoss(){
        float  currentPrice = 0;
        float buyPrice = 0;

        if (egetAktier.isEmpty()){
            return 0;
        }
        for (Aktie a : egetAktier){
            buyPrice += a.getPrice();
        }
        for (Transactions t : egneTransactions){
            currentPrice = t.getPris();
        }
        return (buyPrice-currentPrice)/currentPrice ;
    }

    public void addUsersTransactionsToList(User user) {
        data.transactions();
        for (Transactions d : data.getTransactions()) {
            if (d.getUserid() == user.getUser_id()) {
                egneTransactions.add(d);
            }
        }
    }

    public void transactionToAktie() {
        data.stockMarket();
        for (Transactions d : egneTransactions) {
            for (Aktie a : data.getAktier()) {
                if (d.getTicker().equals(a.getTicker())) {
                    egetAktier.add(a);
                }
            }
        }
    }

    public Aktie printEgetAktier() {
        for (Aktie a : egetAktier) {
            return a;
        }
        return null;
    }

    public int MaengdeAktier() {
        int i = 0;
        transactionToAktie();
        for (Aktie a : egetAktier) {
            i++;
        }
        return i;
    }







    @Override

    public String toString (){
        return "Balance: "+balance+ " Mængde: "+maengde+ " Ticker: "+ticker+ "Aktier eget: "+ "Profit/Loss: "+ calculateProfitLoss() + printEgetAktier();

    }



}