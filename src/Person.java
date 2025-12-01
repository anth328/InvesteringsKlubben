import java.time.LocalDate;

public class Person {

    private String fullName;
    private String email;
    private LocalDate date;

    Person(String fullName, String email, LocalDate date){
        this.fullName = fullName;
        this.email = email;
        this.date = date;
    }
    
}
