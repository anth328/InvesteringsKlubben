import java.lang.reflect.Array;
import java.time.LocalDate;
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
    private DataRepository data;

    public Portfolio(DataRepository data){
        this.data = data;
    };

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

   /* public void calculateBalance(User user) {
        float balance = user.getInitialCash();
        for (Transactions t : egneTransactions) {
            if (t.getOrder().equalsIgnoreCase("buy"))
            {
                    balance -= t.getPrice();
                }
                if (Objects.equals(t.getOrder(), "sell")) {
                    balance += t.getPrice();
                }
            }
            setBalance(balance);
        }
*/

    public void calculateBalance(User user) {

        float currentBalance = user.getInitialCash(); // startbeløb

        for (Transactions t : egneTransactions) {

            if (t.getOrder().equalsIgnoreCase("buy")) {
                currentBalance -= t.getPrice() * t.getQuantity();
            }

            if (t.getOrder().equalsIgnoreCase("sell")) {
                currentBalance += t.getPrice() * t.getQuantity();
            }
        }

        setBalance(currentBalance);  // <-- KUN ÉN GANG, EFTER LOOP
    }


    public float calculateProfitLoss() {
        if (egetAktier.isEmpty()) {
            return 0;
        }
        float aktiePrice = egetAktier.get(0).getPrice();
        return (maengde * aktiePrice) - (maengde * buyPrice);
    }

    public float calculateAllProfitLoss(User user){
        addUsersTransactionsToList(user);
        transactionToAktie();

        float totalBuyValue = 0f;
        float totalCurrentValue = 0f;

        for (int i = 0; i < egneTransactions.size(); i++) {
            Transactions t = egneTransactions.get(i);

            Aktie matchingAktie = null;
            for (Aktie a : data.getAktier()) {
                if (a.getTicker().equals(t.getTicker())) {
                    matchingAktie = a;
                    break;
                }
            }
            if (matchingAktie == null) {
                continue;
            }

            float transactionPrice = t.getPrice();
            float marketPrice = matchingAktie.getPrice();

            if ("buy".equalsIgnoreCase(t.getOrder())) {
                totalBuyValue += transactionPrice;
                totalCurrentValue += marketPrice;
            } else if ("sell".equalsIgnoreCase(t.getOrder())) {
                totalBuyValue -= transactionPrice;
                totalCurrentValue -= marketPrice;
            }
        }
        return ((totalCurrentValue - totalBuyValue)/totalBuyValue) * 100;
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


    public void buyAktie(User user, Aktie aktie, int quantity)
    {
        if (quantity <= 0) {
            System.out.println("Ugyldigt antal.");
            return;
        }

        float totalPrice = aktie.getPrice() * quantity;

        if (totalPrice > balance) {
            System.out.println("Ikke nok penge til at købe " + quantity + " stk af " + aktie.getTicker());
            return;
        }

        balance -= totalPrice;

        // Tilføj aktier til portefølje
        for (int i = 0; i < quantity; i++) {
            egetAktier.add(aktie);
        }

        // Opret transaktion
        Transactions transaction = new Transactions(
                data.getTransactions().size() + 1,
                user.getUser_id(),
                LocalDate.now(),
                aktie.getTicker(),
                aktie.getPrice(),
                "DKK",
                "buy",
                quantity
        );

        egneTransactions.add(transaction);
        data.addTransaction(transaction);
        data.saveTransactionToFile(transaction);


        System.out.println("KØB GENNEMFØRT");
        System.out.println("Du købte " + quantity + " x " + aktie.getTicker());
        System.out.println("Total pris: " + totalPrice);
        System.out.println("Ny balance: " + balance);
    }




    public void sellAktie(User user, Aktie aktie, int quantity)
    {
        if (quantity <= 0) {
            System.out.println("Ugyldigt antal.");
            return;
        }

        // Tæl hvor mange bruger ejer af denne aktie
        int owned = 0;
        for (Aktie a : egetAktier) {
            if (a.getTicker().equalsIgnoreCase(aktie.getTicker())) {
                owned++;
            }
        }

        // Kan ikke sælge mere end man ejer
        if (owned < quantity) {
            System.out.println("FEJL: Du kan ikke sælge " + quantity + " stk af " + aktie.getTicker() +
                    " fordi du kun ejer " + owned);
            return;
        }

        // Beregn total pris
        float totalPrice = aktie.getPrice() * quantity;

        // Læg penge til balance
        balance += totalPrice;

        // Fjern eget aktier fra listen
        int removed = 0;
        for (int i = 0; i < egetAktier.size() && removed < quantity; i++) {
            if (egetAktier.get(i).getTicker().equalsIgnoreCase(aktie.getTicker())) {
                egetAktier.remove(i);
                i--;
                removed++;
            }
        }

        // Opret salgs-transaktion
        Transactions transaction = new Transactions(
                data.getTransactions().size() + 1,
                user.getUser_id(),
                LocalDate.now(),
                aktie.getTicker(),
                aktie.getPrice(),
                "DKK",
                "sell",
                quantity
        );

        egneTransactions.add(transaction);
        data.addTransaction(transaction);
        data.saveTransactionToFile(transaction);

        System.out.println("SALG GENNEMFØRT");
        System.out.println("Du solgte " + quantity + " x " + aktie.getTicker());
        System.out.println("Total pris: " + totalPrice);
        System.out.println("Ny balance: " + balance);
    }

    public void rankList() {
        float profit = 0;
        ArrayList<String> rankList = new ArrayList<>();

        if (data.getUsers().isEmpty()) {
            data.bruger();
        }

        for (User u : data.getUsers()) {
            profit = (int) calculateAllProfitLoss(u);
            rankList.add("Navn: " + u.getName() + " Profit: " + profit + "%");
        }

        for (String s : rankList) {
            System.out.println(s);
        }
    }




    @Override

    public String toString (){
        return "Balance: "+balance+ " Mængde: "+maengde+ " Ticker: "+ticker+ "Aktier eget: "+ "Profit/Loss: "+ calculateProfitLoss() + printEgetAktier();

    }



}