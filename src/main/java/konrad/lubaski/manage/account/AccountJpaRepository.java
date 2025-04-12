package konrad.lubaski.manage.account;



import java.util.List;

public interface AccountJpaRepository {

    void save(AccountEntity accountEntity);

    List<AccountEntity> findAll();

    AccountEntity getReferenceById(Integer accountId);
}