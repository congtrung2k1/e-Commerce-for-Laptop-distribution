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

// Root only
    @GetMapping("/create/{orderId}")
    public String shipmentCreate(@PathVariable("customerId") String orderId) {
        Integer order_id = Integer.valueOf(orderId);
        System.out.println("create " + order_id);
        try {
            Shipment shipment = new Shipment(order_id);
            shipmentService.save(shipment);
            List<Shipment> shipmentList = shipmentService.getAllShipment();
            for (Shipment tmp: shipmentList){
                if (tmp.getOrderId() == order_id) 
                    return String.valueOf(tmp.getShipmentId());
            }
            return "{\"errorMessage\":\"Internal Server error\"}";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }    
    
// Show shipmentId by shipment_id, user view only
    @GetMapping("/edit/{shipmentId}")
    public String getShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        try {
            Shipment shipment = shipmentService.getShipment(shipment_id);
            return jsonMapper.writeValueAsString(shipment);
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"errorMessage\":\"Internal Server error\"}";
        }
    }
    
// Update Shipment done
// Root only
    @PostMapping("/edit/{shipmentId}/done")
    public String updateShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "done");
        orderService.updateOrder(shipment.getOrderId(), "done");
        
        return jsonMapper.writeValueAsString(shipment);
    }
    
    @PostMapping("edit/{shipmentId}/reject")
    public String rejectShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "reject");
        orderService.updateOrder(shipment.getOrderId(), "reject");
        
        return jsonMapper.writeValueAsString(shipment);
    }
}
