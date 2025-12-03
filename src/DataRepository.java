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

    public void stockMarket() {
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
                addAktie(aktie);

            }

        } catch (IOException e) {
            System.out.println("Error: fejl i stockmarket.csv");
            ;
        }
    }

    public void bruger() {
        List<User> brugerListe = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line = br.readLine();
            User user = null;
            while ((line = br.readLine()) != null) {
                String[] userData = line.split(semiColon);
                int user_id = Integer.parseInt(userData[0]);
                String full_name = userData[1];
                String email = userData[2];
                LocalDate birth_date = LocalDate.parse(userData[3], formatter);
                float initial_cash_DKK = Float.parseFloat(userData[4]);
                LocalDate created_at = LocalDate.parse(userData[5], formatter);
                LocalDate lastUpdated = LocalDate.parse(userData[6], formatter);

                user = new User(user_id, full_name, email, birth_date, initial_cash_DKK, created_at, lastUpdated);
                addUsers(user);
            }

        } catch (IOException e) {
            System.out.println("Error: fejl i users.csv");
            ;
        }
    }

    public static List<Valuta> valutaer() {
        List<Valuta> valutaList = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        try (BufferedReader br = new BufferedReader(new FileReader("currency.csv"))) {
            String line = br.readLine();
            Valuta valuta = null;
            while ((line = br.readLine()) != null) {
                String[] valutaData = line.split(semiColon);
                String base_currency = valutaData[0];
                String quote_currency = valutaData[1];
                float rate = Float.parseFloat(valutaData[2].replace(",", "."));
                LocalDate lastUpdated = LocalDate.parse(valutaData[3], formatter);

                valuta = new Valuta(base_currency, quote_currency, rate, lastUpdated);
                valutaList.add(valuta);
            }
        } catch (IOException e) {
            System.out.println("Error: fejl i stockmarket.csv");
            ;
        }

        return valutaList;
    }
    /*public static List<Bond> Obligationer() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-dd-MM");
        List<Bond> bondList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("bondMarket.csv"))) {
            String line = br.readLine();
            Bond bond = null;
            while ((line = br.readLine()) != null) {
                String[] bondData = line.split(semiColon);
                String ticker = bondData[0];
                String name = bondData[1];
                float price = Float.parseFloat(bondData[2].replace(",", "."));
                String currency = bondData[3];
                float coupon_rate = Float.parseFloat(bondData[4].replace(",", "."));
                 LocalDate issue_date = LocalDate.parse(bondData[5], formatter);
                 LocalDate maturity_date = LocalDate.parse(bondData[6], formatter);
                 String rating = bondData[7];
                 String market = bondData[8];
                 LocalDate last_updated = LocalDate.parse(bondData[9], formatter);
            }
        } catch (IOException e) {
            System.out.println("Error: fejl i stockmarket.csv");
        }
        return bondList;
    }*/

    public DataRepository() {
            aktier = new ArrayList<>();
            portfolioList = new ArrayList<>();
            valutaer = new ArrayList<>();
            users = new ArrayList<>();
            personer = new ArrayList<>();
            transactions = new ArrayList<>();
        }

        public void indlæs () {
            System.out.println("Indlæser data");
        }


        public ArrayList<Aktie> getAktier () {
            return aktier;
        }

        public ArrayList<Portfolio> getPortfolioList () {
            return portfolioList;
        }

        public ArrayList<Valuta> getValutaer () {
            return valutaer;
        }

        public ArrayList<User> getUsers () {
            return users;
        }

        public ArrayList<Person> getPersoner () {
            return personer;
        }

        public ArrayList<Transactions> getTransactions () {
            return transactions;
        }

        public void addTransaction (Transactions t){
            transactions.add(t);
        }

        public void addAktie (Aktie aktie)
        {
            aktier.add(aktie);
        }

        public void printAllTransactions () {
            for (Transactions t : transactions) {
                System.out.println(t);


            }
        }

        public void addUsers (User user){
            users.add(user);
        }


    }

