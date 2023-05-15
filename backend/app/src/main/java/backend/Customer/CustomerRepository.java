package backend.Customer;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    @Query(value = "SELECT customers.customer_id FROM customers", nativeQuery = true)
    List<String> getCustomer();

    @Query(value = "SELECT orders.order_id FROM orders WHERE orders.customer_id = ?1", nativeQuery = true)
    List<String> getCustomerOrder(Integer customer_id);
    
    @Modifying
    @Query(value = "UPDATE customers SET name = ?2, password = ?3, phone = ?4, email = ?5, address = ?6, country = ?7 WHERE customer_id = ?1", nativeQuery = true)
    void updateCustomer(Integer customer_id, String name, String password, String phone, String email, String address, String country);
    
    Customer getCustomerByPhone(String phone);
}