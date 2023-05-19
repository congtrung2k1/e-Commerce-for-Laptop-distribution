package backend.Shipment;

import backend.Order.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/shipment")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;
    
    @Autowired
    private OrderService orderService;
    
// Show shipmentId by shipment_id, user view only
    @GetMapping("/edit/{shipmentId}")
    public Shipment getShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {       
        Integer shipment_id = Integer.valueOf(shipmentId);
        return shipmentService.getShipment(shipment_id);
    }
    
// Update Shipment done
// Root only
    @PostMapping("/edit/{shipmentId}/done")
    public Shipment updateShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {       
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "done");
        orderService.updateOrderStatus(shipment.getOrderId(), "done");
        return shipment;
    }
    
    @PostMapping("edit/{shipmentId}/reject")
    public Shipment rejectShipment(@PathVariable("shipmentId") String shipmentId) throws Exception {      
        Integer shipment_id = Integer.valueOf(shipmentId);
        Shipment shipment = shipmentService.updateShipment(shipment_id, "reject");
        orderService.updateOrderStatus(shipment.getOrderId(), "reject");
        return shipment;
    }
}
