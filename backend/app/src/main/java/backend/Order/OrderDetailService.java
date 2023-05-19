package backend.Order;

import backend.Product.ProductService;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailService {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    
    @Autowired
    private ProductService productService;
    
    @Autowired OrderService orderService;

    public Iterable<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
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
            Integer order_id = tmpOrderDetail.get().getOrderId();
            orderDetailRepository.deleteById(idx);
            orderService.updateOrderPrice(order_id);
        } else {
            throw new Exception("Cannot find orderdetail with id: " + idx);
        }
    }
        
    public OrderDetail updateOrderDetail(Integer idx, Integer order_id, Integer product_id, Integer quantity) throws Exception {
        double price = productService.getProductByProductId(product_id).getPrice() * quantity;
        orderDetailRepository.updateOrderDetail(idx, price, quantity);
        return getOrderDetailByIdx(idx);
    }

    public List<OrderDetail> getAllOrderDetail(Integer order_id) throws Exception {
        List<OrderDetail> res = new ArrayList<>();
        List<String> orderDetailList = orderDetailRepository.getOrderDetailListBelongToOrder(order_id);
        for (String idx: orderDetailList) {
            res.add(getOrderDetailByIdx(Integer.valueOf(idx)));
        }
        return res;
    }
}
