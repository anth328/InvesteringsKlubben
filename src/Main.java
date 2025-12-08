import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        DataRepository transaction = new DataRepository();
        transaction.addTransaction(new Transactions(1, 10, "AAPL", 150, 5, 1, 20240101, 1, null));
        transaction.addTransaction(new Transactions(2, 12, "TSLA", 220, 2, 1, 20240102, 1, null));

        transaction.printAllTransactions();
    }



}