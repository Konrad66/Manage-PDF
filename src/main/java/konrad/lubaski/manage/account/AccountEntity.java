package konrad.lubaski.manage.account;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.List;

@Entity
public class AccountEntity {

    @Id
    @GeneratedValue
    private int id;
    @ElementCollection
    private List<String> employeesMails;
    private String email;
    private String password;

    public AccountEntity(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public AccountEntity(List<String> employeesMails, String email, String password) {
        this.employeesMails = employeesMails;
        this.email = email;
        this.password = password;
    }

    //todo bezparametrowy konstruktor, gettery i settery tam gdzie mamy komunikacje z bazą danych oraz mapowaniem z JSON
    public AccountEntity() {
    }

    public List<String> getEmployeesMails() {
        return employeesMails;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email'=" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}