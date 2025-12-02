import java.time.LocalDate;

public abstract class Person {

    protected String fullName;
    protected String email;
    protected LocalDate birthDate;

    Person(String fullName, String email, LocalDate birthDate){
        this.fullName = fullName;
        this.email = email;
        this.birthDate = birthDate;
    }

    public String getFullname(){
        return fullName;
    }

    public String getEMail(){
        return email;
    }

    public LocalDate getBirthDate(){
        return birthDate;
    }
}
