package konrad.lubaski.manage.account;

import konrad.lubaski.manage.common.PasswordEncoderConfig;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Component
public class AccountService implements UserDetailsService {

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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//      return   User.withUsername("")
//                .password("")
//                .roles("USER")
//                .build();
        return accountJpaRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("not found"));
    }
}