import javax.xml.crypto.Data;
import java.time.LocalDate;

public static void main(String[] args){
    DataRepository transaction = new DataRepository();

    Transactions t1 = new Transactions(1, 10, "AAPL", 150, 5, 1, 20240101, 1, null);
    Transactions t2 = new Transactions(2, 12, "TSLA", 220, 2, 1, 20240102, 1, null);

    transaction.addTransaction(t1);
    transaction.addTransaction(t2);

    transaction.saveTransactionToFile(t1);
    transaction.saveTransactionToFile(t2);

    transaction.printAllTransactions();

    System.out.println("Transaktion gemt i transactions.csv");

    transaction.readTransactionsFromFile();


}