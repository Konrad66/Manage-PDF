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
    public AccountDTO getAccounts(@PathVariable Integer accountId) {
        return accountService.getAccount(accountId);
    }

    @GetMapping("/accounts")
    public Collection<AccountDTO> getAllAccounts() {
        return accountService.getAccounts();
    }

    @PostMapping("/accounts")
    public void addAccount(@RequestBody AccountDTO accountDTO) {
        accountService.addAccount(accountDTO);
    }
}


//todo @RequestBody ->