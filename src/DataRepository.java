import java.util.ArrayList;

public class DataRepository {

    private ArrayList<Aktier> aktier;
    private ArrayList<Portfolio> portfolioList;
    private ArrayList<Valuta> valutaer;
    private ArrayList<User> users;
    private ArrayList<Person> personer;

    public DataRepository() {
        aktier = new ArrayList<>();
        portfolioList = new ArrayList<>();
        valutaer = new ArrayList<>();
        users = new ArrayList<>();
        personer = new ArrayList<>();
    }

    public void indlæs() {
        System.out.println("Indlæser data");
    }


    public ArrayList<Aktier> getAktier() {
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
}
