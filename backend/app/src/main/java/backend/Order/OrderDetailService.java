package backend.Order;

import backend.Product.ProductService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private ProductService productService;

    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }
    
    public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> allOrderDetail = (List<OrderDetail>) orderDetailRepository.findAll();
        return allOrderDetail;
    }
    
    public OrderDetail getOrderDetailByIdx(Integer idx) {
        return orderDetailRepository.findById(idx).get();
    }
    
    public String getIndexOfProductIdInOrderId(int order_id, int product_id) {
        List<String> idxList = orderDetailRepository.getIndexOfProductIdInOrderId(order_id, product_id);
        if (idxList.size() == 1) {
            return idxList.get(0);
        } else {
            return "-1";
        }
    }

    public void save (OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
    
    public void deleteOrderDetail(int idx) throws Exception {
        Optional<OrderDetail> tmpOrderDetail = orderDetailRepository.findById(idx);
        if (tmpOrderDetail.isPresent()) {
            orderDetailRepository.deleteById(idx);
        } else {
            throw new Exception("Cannot find user with id: " + idx);
        }
    }
        
    public OrderDetail updateOrderDetail(Integer order_id, Integer product_id, Integer quantity) throws Exception {
        double price = productService.getProductByProductId(product_id).getPrice() * quantity;
        List<String> idxList = orderDetailRepository.getIndexOfProductIdInOrderId(order_id, product_id);
        if (idxList.size() == 1) {
            Integer idx = Integer.valueOf(idxList.get(0));
            orderDetailRepository.updateOrderDetail(idx, price, quantity);
            return orderDetailRepository.findById(idx).get();
        }
        return null;
    }
}
