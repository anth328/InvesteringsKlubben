import javax.xml.crypto.Data;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        LocalDate date = LocalDate.now();

        Valuta valuta = new Valuta("DKK","test",1,date);




        System.out.println(DataRepository.stockMarket());
        System.out.println(DataRepository.bruger());
        System.out.println(DataRepository.valutaer());
        System.out.println(DataRepository.Obligationer());
    }
}
