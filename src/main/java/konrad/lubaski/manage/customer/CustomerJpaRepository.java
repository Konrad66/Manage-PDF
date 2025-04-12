package konrad.lubaski.manage.customer;

//import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerJpaRepository {

    List<CustomerEntity> findAll();
}