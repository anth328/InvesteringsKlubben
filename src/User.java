import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;
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

    public User UsernameMatch(){
        DataRepository data = new DataRepository();
        data.bruger();

        System.out.println("Velkommen til Investerings Klubben");
        System.out.println("Skriv mail for at login: ");
        String mail = sc.nextLine();

        for (User u : data.getUsers()){

            System.out.println("Bruger ID:");
            int id = sc.nextInt();

            if (Objects.equals(mail, u.getEMail())){
                if(id == u.getUser_id()) {
                    System.out.println("You have logged in as " + u.getFullname());
                    return u;
                }
                else {
                    System.out.println("Failed Login Try Again");
                    return null;
                }
            }
            else {
                System.out.println("Failed Login Try Again");
                return null;
            }
        }
        System.out.println("Failed Login Try Again");
        return null;
    }

    @Override

    public String toString() {
        return "User id: " + user_id + " Full name: " + full_name + " Email: " + email + " Birth_date: " + birth_date + " initial cash DKK: " + initial_cash_DKK + " Createdd at: " + created_at+ "Last Updated: " + last_updated;
    }
}