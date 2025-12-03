import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        DataRepository data = new DataRepository();
        LocalDate date = LocalDate.now();

        Valuta valuta = new Valuta("DKK","test",1,date);





        data.stockMarket();
        data.bruger();
        System.out.println(data.getUsers());
        System.out.println(data.getAktier());

        System.out.println(DataRepository.valutaer());
        System.out.println(DataRepository.Obligationer());
    }
}
