import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        DataRepository data = new DataRepository();
        LocalDate date = LocalDate.now();

        Valuta valuta = new Valuta("DKK","test",1,date);
        User user = new User(1,"Maria Jensen","maria.jensen@email.com",date,100000,date,date);





        data.stockMarket();
        data.bruger();
        data.valutaer();
        data.bonds();
        System.out.println(data.getUsers());
        System.out.println(data.getAktier());
        System.out.println(data.getValutaer());
        System.out.println(data.getBonds());



        //System.out.println(DataRepository.Obligationer());
        user.UsernameMatch();
    }
}
