import java.util.ArrayList;

public interface IDataRepository {

    void stockMarket();
    void bruger();
    void bonds();
    void transactions();
    void currency();

    void saveTransactionToFile(Transactions t);

    ArrayList<Aktie> getAktier();
    ArrayList<Portfolio> getPortfolioList();
    ArrayList<User> getUsers();
    ArrayList<Person> getPersoner();
    ArrayList<Bond> getBonds();
    ArrayList<Transactions> getTransactions();
    ArrayList<Currency> getCurrency();

    void addAktie(Aktie aktie);
    void addUsers(User user);
    void addBonds(Bond bond);
    void addTransaction(Transactions t);
    void addCurrency(Currency currency);

    void printAktier();
    void printAktierBasic();
    void printAktieFull(int index);

    void printBonds();
    void printBondsBasic();
    void printBondFull(int index);

    void printTransactions();
    void printAllTransactions();

    void printUsers();
}