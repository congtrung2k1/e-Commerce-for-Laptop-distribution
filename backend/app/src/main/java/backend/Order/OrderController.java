package backend.Order;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.Customer.CustomerService;
import backend.Product.Product;
import backend.Product.ProductRepository;
import backend.Shipment.Shipment;
import backend.Shipment.ShipmentService;

@RestController
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private OrderDetailService orderDetailService;
    
    @Autowired
    private CustomerService customerService;
    
    @Autowired
    private ShipmentService shipmentService;
    
    @Autowired   
    private ProductRepository productRepository;
    
    @PostMapping("/create/{customerId}")
    public Order createOrder(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        Integer shipmentId = 0;
        double amount = 0.0;
        String description = "None";
        String shippingAddr = customerService.getAddrByCustomerId(customer_id);
        String orderStatus = "pending";
        double discount = 0.0;
        
        Order order = new Order(customer_id, shipmentId, amount, description, shippingAddr, orderStatus, discount);
        orderService.save(order);
        List<Order> orderList = orderService.getAllOrder();
        for (Order x: orderList) 
            if (x.getCustomerId() == customer_id && x.getOrderStatus().equals(orderStatus))
                return x;
        return null;
    }
    
// get all order
// root
    @GetMapping("/root")
    public List<Order> getAllOrderRoot() throws Exception {
        return (List<Order>) orderService.findAll();
    }
    
// get all order
// user
    @GetMapping("/user/{customerId}")
    public List<Order> getAllOrderUser(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        List<Order> res = new ArrayList<>();
        List<Order> orderList = (List<Order>) orderService.findAll();
        for (Order tmp: orderList) {
            if (tmp.getCustomerId() == customer_id) 
                res.add(tmp);
        }
        return res;
    }

    
// Get order by order_status - root only
    @GetMapping("/{order_status}")
    public List<Order> getOrderByStatusRoot(@PathVariable("order_status") String order_status) throws Exception {
        List<Order> result = new ArrayList<>();
        List<Order> orderList = orderService.getAllOrder();
        for (Order order: orderList) {
            if (order.getOrderStatus().equals(order_status)) {
                result.add(order);
            }
        }
        return result;
    }
    
// Get order by order_status - user only
    @GetMapping("/{customerId}/{order_status}")
    public List<Order> getOrderByStatusUser(@PathVariable("customerId") String customerId, @PathVariable("order_status") String order_status) throws Exception {
        List<Order> result = new ArrayList<>();
        Integer customer_id = Integer.valueOf(customerId);
        List<String> orderList = customerService.getCustomerOrder(customer_id);
        for (String orderId: orderList) {
            Order order = orderService.getOrderByOrderId(Integer.parseInt(orderId));
            if (order.getOrderStatus().equals(order_status)) {
                result.add(order);
            }
        }
        return result;
    }

// Get OrderDetail list
    @GetMapping("/{orderId}/get")
    public List<OrderDetail> getAllOrderDetail(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        return orderDetailService.getAllOrderDetail(order_id);
    }
    
// Get list Product
    @GetMapping("/{orderId}/getproduct")
    public List<Product> getAllProductByOrderDetail(@PathVariable("orderId") String orderId) throws Exception {
        List<OrderDetail> tmpList = getAllOrderDetail(orderId);
        List<Product> result = new ArrayList<>();
        for (OrderDetail tmp: tmpList) {
            result.add(productRepository.findById(tmp.getProductId()).get());
        }
        return result;
    }
    
// Submit order
    @PostMapping("/{orderId}/submit")
    public Order submitOrder(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        return orderService.updateOrderStatus(order_id, "submit");
    }
    
// Get order by order_id
    @GetMapping("/edit/{orderId}")
    public Order getOrder(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);        
        return orderService.getOrderByOrderId(order_id);
    }
    
// Update Order
    @PostMapping("/edit/{orderId}/update")
    public Order updateOrder(@RequestBody HashMap<String, String> order_form, @PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        String description = order_form.get("description");
        String shippingAddr = order_form.get("shippingAddr");
        String orderStatus = order_form.get("order_status");
        double discount = Double.parseDouble(order_form.get("discount"));
        
        orderService.updateOrder(order_id, 0.0, description, shippingAddr, orderStatus, discount);
        orderService.updateOrderPrice(order_id);
        return orderService.getOrderByOrderId(order_id);
    }
    
// Start shipping
// root only
    @PostMapping("/{orderId}/approve")
    public Shipment approveOrder(@PathVariable("orderId") String orderId) throws Exception {        
        Integer order_id = Integer.valueOf(orderId);
        Shipment shipment = new Shipment(order_id);
        shipmentService.save(shipment);

        List<Shipment> shipmentList = (List<Shipment>) shipmentService.findAll();
        for (Shipment tmpShipment: shipmentList) {
            if (tmpShipment.getOrderId() == order_id) {
                shipment = shipmentService.getShipment(tmpShipment.getShipmentId());
                shipmentService.updateShipment(shipment.getShipmentId(), "pending");
                
                orderService.updateOrderStatus(order_id, "shipping");
                orderService.updateOrderShipment(order_id, shipment.getShipmentId());
                return shipment;
            }
        }
        return null;
    }

// Reject order
    @PostMapping("/{orderId}/reject")
    public Order rejectOrder(@PathVariable("orderId") String orderId) throws Exception {
        Integer order_id = Integer.valueOf(orderId);
        return orderService.updateOrderStatus(order_id, "reject");
    }
}
