import java.time.LocalDate;
import java.util.Scanner;

public class MainTestMenu {
    public static void main(String[] args){

        DataRepository data = new DataRepository();
        data.bruger();
        data.stockMarket();
        data.bonds();
        data.transactions();

        LocalDate date = LocalDate.now();

        User user = new User(11,"Marie Rosen","Marierosen@gmail.com",date,100000,date,date);
        User aktiveUser = user.UsernameMatch();

        boolean menuRun = true;

        System.out.println("Velkommen til Investerings klubben: " + aktiveUser.getFullname());
        System.out.println("Nu kan du indtaste et tal for at bruge programmets funktioner");
        System.out.println("Skriv 1 for hjælp");
        while (menuRun){
            Scanner sc = new Scanner(System.in);
            int valg = sc.nextInt();
            switch(valg){
                case 1:
                {
                    System.out.println("1: for hjælp");
                    System.out.println("2: Se aktiemarkedet");
                    System.out.println("3: Se obligationer");
                    System.out.println("4: Køb aktier");
                    System.out.println("5: Vis alle transaktioner");
                    break;

                }

                case 2:
                {
                    data.printAktier();
                    System.out.println("Vil du købe aktier?, tryk 4");
                    break;

                }

                case 3:
                {
                    data.printBonds();
                    break;
                }
                case 4:
                {
                    break;
                }
                case 5:
                {
                    data.printTransactions();
                    break;
                }
            }
        }





    }
}
