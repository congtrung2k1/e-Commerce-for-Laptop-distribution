package Order;

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
    
    public Order getOrder(int order_id) throws Exception {
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
    
    public Order updateOrder(Integer order_id, String name, String password, String phone, String email, String address, String country) throws Exception {
        orderRepository.updateOrder(order_id, name, password, phone, email, address, country);
        return getOrder(order_id);
    }
}
