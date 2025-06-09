package konrad.lubaski.manage.account;

import konrad.lubaski.manage.common.PasswordEncoderConfig;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class AccountService {

    private AccountJpaRepository accountJpaRepository;
    private PasswordEncoder passwordEncoder;

    public AccountService(AccountJpaRepository accountJpaRepository, PasswordEncoder passwordEncoder) {
        this.accountJpaRepository = accountJpaRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AccountDTO getAccount(Integer accountId) {
        AccountEntity referenceById = accountJpaRepository.getReferenceById(accountId);
        return new AccountDTO(
                referenceById.getId(),
                referenceById.getEmployeesMails(),
                referenceById.getEmail(),
                referenceById.getPassword()
        );
    }

    public Collection<AccountDTO> getAccounts() {
        List<AccountEntity> entities = accountJpaRepository.findAll();
        List<AccountDTO> accountDTOList = new ArrayList<>();
        for (AccountEntity entity : entities) {
            AccountDTO accountDTO = new AccountDTO(
                    entity.getId(),
                    entity.getEmployeesMails(),
                    entity.getEmail(),
                    entity.getPassword()
            );
            accountDTOList.add(accountDTO);
        }
        return accountDTOList;
    }

    public void addAccount(AccountDTO accountDTO) {
        AccountEntity accountEntity = new AccountEntity(accountDTO.getEmployeesMails(), accountDTO.getEmail(), passwordEncoder.encode(accountDTO.getPassword()));
        accountJpaRepository.save(accountEntity);
    }
}