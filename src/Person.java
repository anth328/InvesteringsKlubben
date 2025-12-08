import java.time.LocalDate;

public abstract class Person {

    protected String full_name;
    protected String email;
    protected LocalDate birth_date;

    Person(String full_name, String email, LocalDate birth_date){
        this.full_name = full_name;
        this.email = email;
        this.birth_date = birth_date;
    }

    public String getFullname(){
        return full_name;
    }

    public String getEMail(){
        return email;
    }

    public LocalDate getBirthDate(){
        return birth_date;
    }
}
