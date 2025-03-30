package konrad.lubaski.manage.account;

import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class AccountController {

    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{accountId}")
    public AccountEntity getAccounts(@PathVariable Integer accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/accounts")
    public Collection<AccountEntity> getAllAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/accounts")
    public void addAccount(@RequestBody AccountEntity accountEntity) {
        accountService.addAccount(accountEntity);
    }
}


//todo @RequestBody ->
//todo dokończyć account i dodać 3 typ danych jako przykład