package Shipment;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    @Query(value = "SELECT * FROM shipments", nativeQuery = true)
    List<String> getShipment();

    @Query(value = "SELECT shipment.shipment_id FROM orders WHERE shipment.shipment_id = ?1", nativeQuery = true)
    List<String> getShipmentByShipmentId(Integer shipment_id);

    @Modifying
    @Query(value = "UPDATE shipments SET shipment_status = ?2 WHERE shipment_id = ?1", nativeQuery = true)
    void updateShipment(Integer shipment_id, String shipment_status);
}