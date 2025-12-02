import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person {
    private int userID;
    private String password;
    private float intitialCash;
    private LocalDate createdAt;
    private LocalDate lastUpdate;
    private UserRole userRole;
    Scanner sc = new Scanner(System.in);
    ArrayList users = new ArrayList<>();
    DataRepository dataRepository;

    public User(int userID, String fullname, String password,String email,LocalDate birthDate,float intitialCash,LocalDate createdAt,LocalDate lastUpdate,UserRole userRole){
        super(fullname,email,birthDate);
        this.userID = userID;
        this.password = password;
        this.intitialCash = intitialCash;
        this.createdAt = createdAt;
        this.lastUpdate = lastUpdate;
        this.userRole = userRole;
    }

    public int UsernameMatch(){
        String userID = sc.nextLine();

        for (User u : dataRepository.getUsers()){
            if (userID.equals(getFullname())){
                return 1;
            }
        }
        System.out.println("Failed Login Try Again");
        return -1;
    }

    @Override
    public String toString(){
        return userID + getFullname() + password + getEMail() + getBirthDate() + intitialCash + createdAt + lastUpdate + userRole;
    }
}
