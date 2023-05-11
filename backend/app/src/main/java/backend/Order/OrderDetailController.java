package backend.Order;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.Product.ProductService;

@RestController
@RequestMapping(path = "/order")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
    
    @Autowired
    private ProductService productService;
    
// Add product to order
    @PostMapping("/order/{orderId}/add")
    public String addOrderDetail(@RequestBody HashMap<String, String> orderDetail_form, @PathVariable("orderId") String orderId) throws Exception {
        
        Integer order_id = Integer.valueOf(orderId);
        Integer product_id = Integer.valueOf(orderDetail_form.get("product_id"));

        String idx = orderDetailService.getIndexOfProductIdInOrderId(order_id, product_id);
        if (!(idx.equals("-1"))) {
            Integer quantity = orderDetailService.getOrderDetailByIdx(Integer.valueOf(idx)).getQuantity();
            orderDetailService.updateOrderDetail(order_id, product_id, quantity + 1);
        }
        else {
            double price = productService.getProductByProductId(product_id).getPrice();
            OrderDetail orderDetail = new OrderDetail(order_id, product_id, price, 1);
            orderDetailService.save(orderDetail);
        }
        return null;
    }
    
// Update each product in order
    @PostMapping("/order/{orderId}")
    public String updateOrderDetail(@RequestBody HashMap<String, String> orderDetail_form, @PathVariable("orderId") String orderId) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer order_id = Integer.valueOf(orderId);
        Integer product_id = Integer.valueOf(orderDetail_form.get("product_id"));
        Integer quantity = Integer.valueOf(orderDetail_form.get("quantity"));
        
        String idx = orderDetailService.getIndexOfProductIdInOrderId(order_id, product_id);
        if (!(idx.equals("-1"))) {
            OrderDetail orderDetail = orderDetailService.updateOrderDetail(order_id, product_id, quantity);
            return jsonMapper.writeValueAsString(orderDetail);
        }
        else {
            double price = productService.getProductByProductId(product_id).getPrice() * quantity;
            OrderDetail orderDetail = new OrderDetail(order_id, product_id, price, quantity);
            orderDetailService.save(orderDetail);
            idx = orderDetailService.getIndexOfProductIdInOrderId(order_id, product_id);
            orderDetail = orderDetailService.getOrderDetailByIdx(Integer.valueOf(idx));
            return jsonMapper.writeValueAsString(orderDetail);
        }
    }
}
