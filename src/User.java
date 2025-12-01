import java.time.LocalDate;

public class User extends Person {
    private int userID;
    private String password;
    private float intitialCash;
    private LocalDate createdAt;
    private LocalDate lastUpdate;
    private UserRole userRole;

    public User(String fullname,int userID, String password,String email,LocalDate birthDate,float intitialCash,LocalDate createdAt,LocalDate lastUpdate,UserRole userRole){
        super(fullname,email,birthDate);
        this.userID = userID;
        this.password = password;
        this.intitialCash = intitialCash;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.userRole = userRole;
    }



}
