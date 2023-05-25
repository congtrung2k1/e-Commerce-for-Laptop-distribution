package backend.Shipment;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    @Query(value = "SELECT shipments.shipment_id FROM shipments WHERE shipments.order_id = ?1", nativeQuery = true)
    String getShipmentByOrderId(Integer orderId);
    
    @Query(value = "SELECT shipments.shipment_id FROM shipments WHERE shipments.order_id IN " + 
                    "(SELECT orders.order_id FROM orders WHERE orders.customer_id = ?1)", nativeQuery = true)
    List<String> getAllByUserId(Integer userId);
    
    @Modifying
    @Query(value = "UPDATE shipments SET shipment_status = ?2 WHERE shipment_id = ?1", nativeQuery = true)
    void updateShipment(Integer shipment_id, String shipment_status);
}