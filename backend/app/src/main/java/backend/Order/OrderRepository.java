package backend.Order;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderRepository extends CrudRepository<Order, Integer> {
    @Query(value = "SELECT * FROM orders WHERE orders.order_id = ?1 ", nativeQuery = true)
    List<Order> getOrder(Integer order_id);

    @Modifying
    @Query(value = "UPDATE orders SET ordername = ?2, password = ?3, phone = ?4, email = ?5, address = ?6, country = ?7 WHERE order_id = ?1", nativeQuery = true)
    void updateOrder(Integer order_id, String name, String password, String phone, String email, String address, String country);
    
    Order findOrderByOrderId(Integer order_id);
}