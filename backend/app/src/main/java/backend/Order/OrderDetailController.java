package backend.Order;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import backend.Product.ProductService;

@RestController
@RequestMapping(path = "/orderdetail")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private OrderService orderService;
    
// Add product to orderID ("pending" status)
    @PostMapping("/{orderId}/add")
    public void addOrderDetail(@RequestBody HashMap<String, String> orderDetail_form, @PathVariable("orderId") String orderId) throws Exception {
        
        Integer order_id = Integer.valueOf(orderId);
        Integer product_id = Integer.valueOf(orderDetail_form.get("product_id"));
        Integer form_quantity = Integer.valueOf(orderDetail_form.get("quantity"));

        String idx = orderDetailService.getIndexOfProductIdInOrderId(order_id, product_id);
        if (!(idx.equals("-1"))) {
            Integer quantity = orderDetailService.getOrderDetailByIdx(Integer.valueOf(idx)).getQuantity();
            orderDetailService.updateOrderDetail(Integer.valueOf(idx), order_id, product_id, quantity + form_quantity);
            orderService.updateOrderPrice(order_id);
        }
        else {
            double price = productService.getProductByProductId(product_id).getPrice();
            OrderDetail orderDetail = new OrderDetail(order_id, product_id, price, 1);
            orderDetailService.save(orderDetail);
            orderService.updateOrderPrice(order_id);
        }
    }
    
// Update each product in /order/edit/{orderId}
    @PostMapping("/{idx_num}/update")
    public OrderDetail updateOrderDetail(@RequestBody HashMap<String, String> orderDetail_form, @PathVariable("idx_num") String idx_num) throws Exception {
        Integer idx = Integer.valueOf(idx_num);
        Integer order_id = Integer.valueOf(orderDetail_form.get("order_id"));
        Integer product_id = Integer.valueOf(orderDetail_form.get("product_id"));
        Integer quantity = Integer.valueOf(orderDetail_form.get("quantity"));
        return orderDetailService.updateOrderDetail(idx, order_id, product_id, quantity);
    }
    
//Delete a product in order.
    @PostMapping("/{idx_num}/remove")
    public void removeOrderDetail(@PathVariable("idx_num") String idx_num) throws Exception {
        Integer idx = Integer.valueOf(idx_num);
        orderDetailService.deleteOrderDetail(idx);
    }
}
