package konrad.lubaski.manage.customer;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomCustomerRepository implements CustomerJpaRepository{
    @Override
    public List<CustomerEntity> findAll() {
        return List.of();
    }
}
