package backend.Shipment;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @GetMapping("/shipment/create")
    public Integer shipmentCreate(@RequestBody HashMap<String, String> shipment_form) {
        Integer order_id = Integer.valueOf(shipment_form.get("order_id"));
        String shipment_status = shipment_form.get("shipment_status");
        System.out.println(order_id);
        try {
            Shipment shipment = new Shipment(order_id, shipment_status);
            shipmentService.save(shipment);
            List<Shipment> shipmentList = shipmentService.getAllShipment();
            for (Shipment tmp: shipmentList){
                if (tmp.getOrderId() == order_id) 
                    return tmp.getShipmentId();
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }    
    
// Get shipment by shipment_id
    @GetMapping("/shipment/{shipmentId}")
    public Shipment getShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        Integer shipment_id = Integer.valueOf(shipmentId);
        List<Shipment> shipmentList = shipmentService.getAllShipment();
        for (Shipment shipment: shipmentList) {
            if (shipment.getShipmentId() == shipment_id) {
                return shipment;
            }
        }
        return null;
    }
    
// Get shipment information for edit
    @GetMapping("shipment/edit/{shipmentId}")
    public String getShipmentUpdate(@PathVariable("shipmentId") String shipmentId) throws Exception {
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.getShipment(shipment_id);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(shipment);
    }
    
// Update Shipment
    @PostMapping("shipment/edit/{shipmentId}/update")
    public String updateShipment(@RequestBody HashMap<String, String> shipment_form, @PathVariable("shipmentId") String shipmentId) {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        String shipment_status = shipment_form.get("shipmentStatus");
        System.out.println(shipment_id);
        try {
            Shipment shipment = shipmentService.updateShipment(shipment_id, shipment_status);
            return jsonMapper.writeValueAsString(shipment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
