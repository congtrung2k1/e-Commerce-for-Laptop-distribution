package backend.Shipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipmentService {
    @Autowired
    private ShipmentRepository shipmentRepository;

    public Iterable<Shipment> findAll() {
        return shipmentRepository.findAll();
    }

    public List<Shipment> getAllShipment() {
        List<Shipment> allShipment = (List<Shipment>) shipmentRepository.findAll();
        return allShipment;
    }
    
    public List<Shipment> getAllByUser(Integer userId) {
        List<Shipment> res = new ArrayList<>();
        List<String> allShipment = shipmentRepository.getAllByUserId(userId);
        for (String tmpShipmentId: allShipment) {
            res.add(shipmentRepository.findById(Integer.valueOf(tmpShipmentId)).get());
        }
        return res;
    }
    
    public Shipment getShipment(int shipment_id) throws Exception {
        Optional<Shipment> tmpShipment = shipmentRepository.findById(shipment_id);
        if (tmpShipment.isPresent()) {
            return tmpShipment.get();
        } else {
            throw new Exception("Shipment with id: " + shipment_id + " not found");
        }
    }
    
    public Shipment getShipmentByOrderId(int orderId) throws Exception {
        String shipmentId = shipmentRepository.getShipmentByOrderId(orderId);
        return shipmentRepository.findById(Integer.valueOf(shipmentId)).get();
    }

    public void save (Shipment shipment) {
        shipmentRepository.save(shipment);
    }
    
    public void deleteShipment(int shipment_id) throws Exception {
        Optional<Shipment> tmpShipment = shipmentRepository.findById(shipment_id);
        if (tmpShipment.isPresent()) {
            shipmentRepository.deleteById(shipment_id);
        } else {
            throw new Exception("Shipment with id: " + shipment_id + " not found");
        }
    }
    
    public String hasShipment(int shipment_id) throws Exception {
        Optional <Shipment> tmpShipment = shipmentRepository.findById(shipment_id);
        if (tmpShipment.isPresent()) {
            return "Available";
        } else {
            throw new Exception("Shipment with id:" + shipment_id + " not found");
        }
    }
    
    public Shipment updateShipment(Integer shipment_id, String shipment_status) throws Exception {
        shipmentRepository.updateShipment(shipment_id, shipment_status);
        return getShipment(shipment_id);
    }
}
