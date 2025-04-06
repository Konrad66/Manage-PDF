package konrad.lubaski.manage.customer;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CustomerService {

    private CustomerJpaRepository customerJpaRepository;

    public CustomerService(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    public Collection<CustomerDTO> getAllCustomers() {
        List<CustomerEntity> entities = customerJpaRepository.findAll();
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (CustomerEntity entity : entities) {
            CustomerDTO customerDTO = new CustomerDTO(
                    entity.getId(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getEmail(),
                    entity.getAge()
            );
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}