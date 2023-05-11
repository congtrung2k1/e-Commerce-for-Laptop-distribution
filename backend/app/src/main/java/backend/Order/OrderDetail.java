package backend.Order;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "order_details")

public class OrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;
    
    @Column(name = "order_id")
    private int orderId;

    @Column(name = "product_id")
    private int productId;

    @Column(name = "price")
    private double price;
    
    @Column(name = "quantity")
    private Integer quantity;

    public OrderDetail() {}
    public OrderDetail(int orderId, int productId, double price, Integer quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getIdx() {
        return idx;
    }
    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idx != null ? idx.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderDetail)) {
            return false;
        }
        OrderDetail other = (OrderDetail) object;
        return !((this.idx == null && other.idx != null) || (this.idx != null && !this.idx.equals(other.idx)));
    }

    @Override
    public String toString() {
        return "Order.OrderDetails[ idx=" + idx + " ]";
    }
    
}
