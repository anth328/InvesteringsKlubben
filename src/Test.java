import java.time.LocalDate;

public class Test {
    public static void main(String[] args){
        LocalDate date = LocalDate.now();

        Valuta valuta = new Valuta("DKK","test",1,date);

        Aktie aktie = new Aktie("n ord","n ord","Sektor",23,valuta,"AAA",22,"DANSK MARKET",date);

        Portfolio p = new Portfolio(2,1000,"test");

        p.addAktier(aktie);
        System.out.println(p);
    }
}
