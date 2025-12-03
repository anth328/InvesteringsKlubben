import java.time.LocalDate;
import java.util.Scanner;

public class MainTestMenu {
    public static void main(String[] args){

        DataRepository data = new DataRepository();
        data.bruger();

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
                case 1:{
                    System.out.println("1: for hjælp");
                    System.out.println("2: minecraft");
                }
                case 2:{
                    System.out.println("minecraft");
                }
            }
        }





    }
}
