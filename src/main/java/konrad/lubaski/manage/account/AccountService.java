package konrad.lubaski.manage.account;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class AccountService {

    private AccountJpaRepository accountJpaRepository;

    public AccountService(AccountJpaRepository accountJpaRepository) {
        this.accountJpaRepository = accountJpaRepository;
    }

    public AccountEntity getAccount(Integer accountId) {
        return accountJpaRepository.getReferenceById(accountId);
    }

    public Collection<AccountEntity> getAccounts() {
        return accountJpaRepository.findAll();
    }

    public void addAccount(AccountEntity accountEntity) {
        accountJpaRepository.save(accountEntity);
    }
}