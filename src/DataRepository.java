import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;


public class DataRepository {

    private ArrayList<Aktie> aktier;
    private ArrayList<Portfolio> portfolioList;
    private ArrayList<Valuta> valutaer;
    private ArrayList<User> users;
    private ArrayList<Person> personer;
    private ArrayList<Transactions> transactions;
    public static final String semiColon = ";";

    public static List<Aktie> stockMarket() {
        List<Aktie> aktieliste = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader("stockmarket.csv"))) {
            String line = br.readLine();
            Aktie aktie = null;
            while ((line = br.readLine()) != null) {
                String[] aktiedata = line.split(semiColon);
                String ticker = aktiedata[0];
                String name = aktiedata[1];
                String sector = aktiedata[2];
                float price = Float.parseFloat(aktiedata[3].replace(",", "."));
                String currency = aktiedata[4];
                String rating = aktiedata[5];
                float dividendYield = Float.parseFloat(aktiedata[6].replace(",", "."));
                String market = aktiedata[7];
                LocalDate lastUpdated = LocalDate.parse(aktiedata[8], formatter);
                aktie = new Aktie(ticker, name, sector, price, currency, rating, dividendYield, market, lastUpdated);
                aktieliste.add(aktie);
            }

        } catch (IOException e) {
            System.out.println("Error: fejl i stockmarket.csv");;
        }
        return aktieliste;
    }

    public DataRepository() {
        aktier = new ArrayList<>();
        portfolioList = new ArrayList<>();
        valutaer = new ArrayList<>();
        users = new ArrayList<>();
        personer = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public void indlæs() {
        System.out.println("Indlæser data");
    }


    public ArrayList<Aktie> getAktier() {
        return aktier;
    }

    public ArrayList<Portfolio> getPortfolioList() {
        return portfolioList;
    }

    public ArrayList<Valuta> getValutaer() {
        return valutaer;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Person> getPersoner() {
        return personer;
    }

    public ArrayList<Transactions> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transactions t) {
        transactions.add(t);
    }

    public void printAllTransactions() {
        for (Transactions t : transactions) {
            System.out.println(t);



        }
    }

    public void addUsers(User user){
        users.add(user);
    }

    public void indlæsUsers(){
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))){
            String line;
            while ((line = br.readLine()) != null){
                String[] split = line.split(";");
                User user = new User(Integer.parseInt(split[0]),split[1],"test",split[2],LocalDate.parse(split[3]),Float.parseFloat(split[4]),LocalDate.parse(split[5]),LocalDate.parse(split[6]),UserRole.Leder);
                addUsers(user);
                System.out.println(user);
            }
        } catch (IOException e) {
            System.out.println("Error");
        }
    }
}
