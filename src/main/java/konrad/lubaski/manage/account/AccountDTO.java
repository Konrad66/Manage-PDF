package konrad.lubaski.manage.account;

import java.util.List;

public class AccountDTO {

    //klasa DTo też musi miec pusty konstruktor i getery do wszystkich pól, jest to wymóg biblioteki JASON
    //todo mechanizm reflekcji w Javie i na czym polega
    private int id;
    private List<String> employeesMails;
    private String email;
    private String password;

    public AccountDTO() {
    }

    public int getId() {
        return id;
    }

    public List<String> getEmployeesMails() {
        return employeesMails;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
