package konrad.lubaski.manage.account;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Integer> {

    //Derived query - sam nagłówek metody
    //@Query - to jest HQL (w uproszczonym sqlu)
    //JDBC - w czystym sql
    Optional<AccountEntity> findByEmail(String email);

}