package backend.Order;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface OrderDetailRepository extends CrudRepository<OrderDetail, Integer> {
    @Query(value = "SELECT order_details.idx FROM order_details WHERE order_details.order_id = ?1 AND order_details.product_id = ?2 ", nativeQuery = true)
    List<String> getIndexOfProductIdInOrderId(Integer order_id, Integer product_id);
    
    @Query(value = "SELECT order_details.idx FROM order_details WHERE order_details.order_id = ?1", nativeQuery = true)
    public List<String> getOrderDetailListBelongToOrder(Integer order_id);

    @Modifying
    @Query(value = "UPDATE order_details SET price = ?2, quantity = ?3 WHERE idx = ?1", nativeQuery = true)
    void updateOrderDetail(Integer idx, double price, Integer quantity);
    
    Order findOrderByOrderId(Integer order_id);
}