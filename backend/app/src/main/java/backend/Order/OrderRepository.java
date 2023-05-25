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
    
    @Query(value = "SELECT order_details.price FROM order_details WHERE order_details.order_id = ?1", nativeQuery = true)
    List<String> getAllProductPrice(Integer order_id);

    @Modifying
    @Query(value = "UPDATE orders SET amount = ?2, description = ?3, shipping_addr = ?4, order_status = ?5, discount = ?6 WHERE order_id = ?1", nativeQuery = true)
    void updateOrder(Integer order_id, double amount, String description, String shippingAddr, String orderStatus, double discount);
    
    @Modifying
    @Query(value = "UPDATE orders SET order_status = ?2 WHERE order_id = ?1", nativeQuery = true)
    void updateOrderStatus(Integer order_id, String order_status);
    
    @Modifying
    @Query(value = "UPDATE orders SET shipment_id = ?2 WHERE order_id = ?1", nativeQuery = true)
    void updateOrderShipment(Integer order_id, Integer shipment_id);
}