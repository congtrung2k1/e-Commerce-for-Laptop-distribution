package backend.Order;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.Customer.CustomerService;
import backend.Shipment.Shipment;
import backend.Shipment.ShipmentService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ShipmentService shipmentService;
    
    @PostMapping("/create/{customerId}")
    public Order createOrder(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        Integer shipmentId = 0;
        double amount = 0.0;
        String description = "None";
        String shippingAddr = customerService.getAddrByCustomerId(customer_id);
        String orderStatus = "Pending";
        double discount = 0.0;
        try {
            Order order = new Order(customer_id, shipmentId, amount, description, shippingAddr, orderStatus, discount);
            orderService.save(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
// Get order by order_status - root only
    @GetMapping("/{order_status}")
    public List<String> getOrderByStatusRoot(@PathVariable("order_status") String order_status) throws Exception {
        List<String> result = null;
        List<Order> orderList = orderService.getAllOrder();
        for (Order order: orderList) {
            if (order.getOrderStatus().equals(order_status)) {
                result.add(String.valueOf(order.getOrderId()));
            }
        }
        return result;
    }
    
// Get order by order_status - user only
    @GetMapping("/{customerId}_{order_status}")
    public List<String> getOrderByStatusUser(@PathVariable("customerId") String customerId, @PathVariable("order_status") String order_status) throws Exception {
        List<String> result = null;
        Integer customer_id = Integer.valueOf(customerId);
        List<String> orderList = customerService.getCustomerOrder(customer_id);
        for (String orderId: orderList) {
            Order order = orderService.getOrderByOrderId(Integer.valueOf(orderId));
            if (order.getOrderStatus().equals(order_status)) {
                result.add(String.valueOf(order.getOrderId()));
            }
        }
        return result;
    }
    
// Get order by order_id
    @GetMapping("/edit/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);        
        Order order = orderService.getOrderByOrderId(order_id);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(order);
    }
    
// Update Order
    @PostMapping("/edit/{orderId}/update")
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
            return "{\"errorMessage\":\"Internal server error\"}";
        }
    }
    
// Start shipping
// root only
    @PostMapping("/{orderId}/approve")
    public String approveOrder(@RequestBody HashMap<String, String> order_form, @PathVariable("orderId") String orderId) throws Exception {        
        ObjectMapper jsonMapper = new ObjectMapper();
        
        try {
            Integer order_id = Integer.valueOf(orderId);
            Shipment shipment = new Shipment(order_id);
            shipmentService.save(shipment);
            orderService.updateOrder(order_id, "shipping");
            
            List<Shipment> shipmentList = (List<Shipment>) shipmentService.findAll();
            for (Shipment tmpShipment: shipmentList) {
                if (tmpShipment.getOrderId() == order_id) {
                    return jsonMapper.writeValueAsString(shipmentService.getShipment(tmpShipment.getShipmentId()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"errorMessage\":\"Internal server error\"}";
        }
        return null;
    }

// Reject order
    @PostMapping("/{orderId}/reject")
    public String rejectOrder(@PathVariable("orderId") String orderId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();

        Integer order_id = Integer.valueOf(orderId);
        Order order = orderService.updateOrder(order_id, "reject");
        
        return jsonMapper.writeValueAsString(order);
    }
}
