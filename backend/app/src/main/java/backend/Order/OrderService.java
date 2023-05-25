package backend.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }
    
    public List<Order> getAllOrder() {
        List<Order> allOrder = (List<Order>) orderRepository.findAll();
        return allOrder;
    }
    
    public Order getOrderByOrderId(int order_id) throws Exception {
        Optional<Order> tmpOrder = orderRepository.findById(order_id);
        if (tmpOrder.isPresent()) {
            return tmpOrder.get();
        } else {
            throw new Exception("Order with id: " + order_id + " not found");
        }
    }

    public void save (Order order) {
        orderRepository.save(order);
    }
    
    public void deleteOrder(int order_id) throws Exception {
        Optional<Order> tmpOrder = orderRepository.findById(order_id);
        if (tmpOrder.isPresent()) {
            orderRepository.deleteById(order_id);
        } else {
            throw new Exception("Order with id: " + order_id + " not found");
        }
    }
    
    public String hasOrder(int order_id) throws Exception {
        Optional <Order> tmpOrder = orderRepository.findById(order_id);
        if (tmpOrder.isPresent()) {
            return "Available";
        } else {
            throw new Exception("Order with id:" + order_id + " not found");
        }
    }
    
    public Order updateOrder(Integer order_id, double amount, String description, String shippingAddr, String orderStatus, double discount) throws Exception {
        orderRepository.updateOrder(order_id, amount, description, shippingAddr, orderStatus, discount);
        return getOrderByOrderId(order_id);
    }
    
    public Order updateOrderShipment(Integer order_id, Integer shipment_id) throws Exception {
        orderRepository.updateOrderShipment(order_id, shipment_id);
        return getOrderByOrderId(order_id);
    }

    public Order updateOrderStatus(Integer order_id, String order_status) throws Exception {
        orderRepository.updateOrderStatus(order_id, order_status);
                System.out.println(order_id);
        return getOrderByOrderId(order_id);
    }
    
    public void updateOrderPrice(Integer order_id) throws Exception {
        Order order = getOrderByOrderId(order_id);
        
        double amount = 0.0 - order.getDiscount();
        List<String> priceList = orderRepository.getAllProductPrice(order_id);
        for (String i: priceList)
            amount += Double.parseDouble(i);

        updateOrder(order_id, amount, order.getDescription(), order.getShippingAddr(), order.getOrderStatus(), order.getDiscount());
    }
}
