package konrad.lubaski.manage.account;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class CustomAccountRepository implements AccountJpaRepository{
    @Override
    public void save(AccountEntity accountEntity) {

    }

    @Override
    public List<AccountEntity> findAll() {
        return List.of();
    }

    @Override
    public AccountEntity getReferenceById(Integer accountId) {
        return null;
    }
}
