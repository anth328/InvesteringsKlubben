import java.time.LocalDate;

public abstract class Person {

    private String fullName;
    private String email;
    private LocalDate birthDate;

    Person(String fullName, String email, LocalDate birthDate){
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }
}
