package konrad.lubaski.manage.account;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class AccountEntity implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_USER"));
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String toString() {
        return "Account{" +
                "email'=" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}