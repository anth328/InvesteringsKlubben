import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.io.IOException;


public class DataRepository {

    private ArrayList<Aktie> aktier;
    private ArrayList<Portfolio> portfolioList;
    private ArrayList<Valuta> valutaer;
    private ArrayList<User> users;
    private ArrayList<Person> personer;
    private ArrayList<Transactions> transactions;

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
