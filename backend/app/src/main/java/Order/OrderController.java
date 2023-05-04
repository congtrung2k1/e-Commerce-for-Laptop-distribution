package Order;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
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
    
// Get order information for edit
    @GetMapping("/edit/{orderId}")
    public String getOrderUpdate(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        Order order = orderService.getOrder(order_id);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(order);
    }
    
// Update Order
    @PostMapping("/edit/{orderId}/update")
    public String updateOrder(@RequestBody HashMap<String, String> order_form, @PathVariable("orderId") String orderId) {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer order_id = Integer.valueOf(orderId);
        String name = order_form.get("name");
        String password = order_form.get("password");
        String phone = order_form.get("phone");
        String email = order_form.get("email");
        String address = order_form.get("address");
        String country = order_form.get("country");
        System.out.println(phone);
        try {
            Order order = orderService.updateOrder(order_id, name, password, phone, email, address, country);
            return jsonMapper.writeValueAsString(order);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "{}";
    }

}
