package backend.Order;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.user.UserService;
import backend.Shipment.Shipment;
import backend.Shipment.ShipmentService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private ShipmentService shipmentService;
    
    @PostMapping("/order/create/{userId}")
    public Order createOrder(@PathVariable("userId") String userId) throws Exception {
        Integer user_id = Integer.valueOf(userId);
        Integer shipmentId = 0;
        double amount = 0.0;
        String description = "None";
        String shippingAddr = userService.getAddrByUserId(user_id);
        String orderStatus = "Pending";
        double discount = 0.0;
        try {
            Order order = new Order(user_id, shipmentId, amount, description, shippingAddr, orderStatus, discount);
            orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
// Get order by order_id
    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        List<Order> orderList = orderService.getAllOrder();
        for (Order order: orderList) {
            if (order.getOrderId() == order_id) {
                return order;
            }
        }
        return null;
    }
    
// Get order by order_status
    @GetMapping("/order/{order_status}")
    public List<String> getOrderByStatus(@PathVariable("order_status") String order_status) throws Exception {
        List<String> result = null;
        List<Order> orderList = orderService.getAllOrder();
        for (Order order: orderList) {
            if (order.getOrderStatus().equals(order_status)) {
                result.add(String.valueOf(order.getOrderId()));
            }
        }
        return result;
    }
    
// Update Order
    @PostMapping("/order/{orderId}/update")
    public String updateOrder(@RequestBody HashMap<String, String> order_form, @PathVariable("orderId") String orderId) {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer order_id = Integer.valueOf(orderId);
        double amount = Double.parseDouble(order_form.get("amount"));
        String description = order_form.get("description");
        String shippingAddr = order_form.get("shippingAddr");
        String orderStatus = order_form.get("order_status");
        double discount = Double.parseDouble(order_form.get("discount"));
        
        try {
            Order order = orderService.updateOrder(order_id, amount, description, shippingAddr, orderStatus, discount);
            return jsonMapper.writeValueAsString(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }
    
// Start shipping
    @PostMapping("/order/{orderId}/approve")
    public String approveOrder(@RequestBody HashMap<String, String> order_form, @PathVariable("orderId") String orderId) throws Exception {        
        Integer order_id = Integer.valueOf(orderId);
        Shipment shipment = new Shipment(order_id);
        shipmentService.save(shipment);
        orderService.updateOrder(order_id, "submit");
        
        List<Shipment> shipmentList = (List<Shipment>) shipmentService.findAll();
        for (Shipment tmpShipment: shipmentList) {
            if (tmpShipment.getOrderId() == order_id) {
                return String.valueOf(tmpShipment.getShipmentId());
            }
        }
        return null;
    }

// Reject order
    @PostMapping("/order/{orderId}/reject")
    public String rejectOrder(@PathVariable("orderId") String orderId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Integer order_id = Integer.valueOf(orderId);
        Order order = orderService.updateOrder(order_id, "reject");
        
        return jsonMapper.writeValueAsString(order);
    }
}
