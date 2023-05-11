package backend.Shipment;
 
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface ShipmentRepository extends CrudRepository<Shipment, Integer> {
    @Modifying
    @Query(value = "UPDATE shipments SET shipment_status = ?2 WHERE shipment_id = ?1", nativeQuery = true)
    void updateShipment(Integer shipment_id, String shipment_status);
}