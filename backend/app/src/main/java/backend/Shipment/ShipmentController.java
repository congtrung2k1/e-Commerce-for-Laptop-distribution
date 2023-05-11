package backend.Shipment;

import backend.Order.*;

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
    
    @Autowired
    private OrderService orderService;

    @GetMapping("/shipment/create")
    public String shipmentCreate(@RequestBody HashMap<String, String> shipment_form) {
        Integer order_id = Integer.valueOf(shipment_form.get("order_id"));
        System.out.println(order_id);
        try {
            Shipment shipment = new Shipment(order_id);
            shipmentService.save(shipment);
            List<Shipment> shipmentList = shipmentService.getAllShipment();
            for (Shipment tmp: shipmentList){
                if (tmp.getOrderId() == order_id) 
                    return String.valueOf(tmp.getShipmentId());
            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
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
    
// Update Shipment
    @PostMapping("shipment/{shipmentId}/done")
    public String updateShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "done");
        orderService.updateOrder(shipment.getOrderId(), "done");
        
        return jsonMapper.writeValueAsString(shipment);
    }
    
    @PostMapping("shipment/{shipmentId}/reject")
    public String rejectShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "reject");
        orderService.updateOrder(shipment.getOrderId(), "reject");
        
        return jsonMapper.writeValueAsString(shipment);
    }
}
