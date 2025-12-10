import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import java.util.Collections;

public class Portfolio {

    private float balance;
    private int maengde;
    private String ticker;
    private float buyPrice;
    private float currentPrice;
    private float profit;

    private ArrayList<Aktie> egetAktier = new ArrayList<>();
    private ArrayList<Transactions> egneTransactions = new ArrayList<>();
    private ArrayList<Portfolio> rankList = new ArrayList<>();

    private DataRepository data;
    private User user;
    private Currency currency = new Currency();

    public Portfolio(DataRepository data){
        this.data = data;
    }

    public Portfolio(User user, float balance,float profit) {
        this.user = user;
        this.balance = balance;
        this.profit = profit;
    }

    public float getProfit(){
        return profit;
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
        addUsersTransactionsToList(user);

        float currentBalance = user.getInitialCash();
        float price = 0;

        for (Transactions t : egneTransactions) {

            if (!Objects.equals(t.getCurrency(), "DKK")){
                for (Currency c : data.getCurrency()){
                    if (c.getCurrency().matches(t.getCurrency())){
                        price = currency.calculateCurrencyToDKK(c, t);
                    }
                }
            }

            if (Objects.equals(t.getCurrency(), "DKK")){
                price += t.getPrice();
            }

            if (t.getOrder().equalsIgnoreCase("buy")) {
                currentBalance -= price * t.getQuantity();
            }

            if (t.getOrder().equalsIgnoreCase("sell")) {
                currentBalance += price * t.getQuantity();
            }
        }

        setBalance(currentBalance);
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
        egneTransactions.clear();
        for (Transactions d : data.getTransactions()) {
            if (d.getUserid() == user.getUser_id()) {
                egneTransactions.add(d);
            }
        }
    }

    public void transactionToAktie() {
        egetAktier.clear();
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
                aktie.getCurrency().getCurrency(),
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
                aktie.getCurrency().getCurrency(),
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
        rankList.clear();

        if (data.getUsers().isEmpty()) {
            data.bruger();
        }

        for (User u : data.getUsers()) {
            profit = (int) calculateAllProfitLoss(u);
            Portfolio portfolio = new Portfolio(u,0,profit);
            rankList.add(portfolio);
        }
        rankList.sort(Comparator.comparing(Portfolio::getProfit).reversed());
        for (Portfolio p : rankList){
            System.out.println(p);
        }
    }

    @Override

    public String toString (){
        return "Navn: " + user.getName() + " Profit: " + profit;

    }

    public void printAllPortfolios() {
        for (Portfolio p : rankList) {
            System.out.println(p);
        }
    }

    public void printPortfolio(User user) {

        addUsersTransactionsToList(user);
        transactionToAktie();
        calculateBalance(user);

        System.out.println("====================================");
        System.out.println("PORTFØLJE FOR: " + user.getFullname());
        System.out.println("Saldo: " + getBalance() + " DKK");
        System.out.println("------------------------------------");

        System.out.println("Aktier i porteføljen:");
        if (getEgneAktier().isEmpty()) {
            System.out.println("  (Ingen aktier)");
        } else {
            for (Aktie a : getEgneAktier()) {
                System.out.println("  " + a);
            }
        }

        System.out.println("\nTransaktioner:");
        if (egneTransactions.isEmpty()) {
            System.out.println("  (Ingen transaktioner)");
        } else {
            for (Transactions t : egneTransactions) {
                System.out.println("  " + t);
            }
        }

        System.out.println("====================================");
    }



    }

