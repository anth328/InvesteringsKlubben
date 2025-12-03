import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class User extends Person {
    private int user_id;
    private String password;
    private float initial_cash_DKK;
    private LocalDate created_at;
    private LocalDate last_updated;
    private UserRole userRole;


    Scanner sc = new Scanner(System.in);
    ArrayList users = new ArrayList<>();
    DataRepository dataRepository;

    public User(int user_id, String full_name, String email,LocalDate birth_date,float initial_cash_DKK,LocalDate created_at,LocalDate last_updated){
        super(full_name,email,birth_date);

        this.user_id = user_id;
        this.initial_cash_DKK = initial_cash_DKK;
        this.created_at = created_at;
        this.last_updated = last_updated;
    }

    public int getUser_id(){
        return user_id;
    }

    public int UsernameMatch(){
        DataRepository data = new DataRepository();
        data.bruger();
        int userID = sc.nextInt();
        for (User u : data.getUsers()){
            if (userID == (u.getUser_id())){
                System.out.println("You have loged in on " + u.getFullname());
                return 1;
            }
        }
        System.out.println("Failed Login Try Again");
        return -1;
    }

    @Override

    public String toString() {
        return "User id: " + user_id + " Full name: " + full_name + " Email: " + email + " Birth_date: " + birth_date + " initial cash DKK: " + initial_cash_DKK + " Createdd at: " + created_at+ "Last Updated: " + last_updated;
    }
}