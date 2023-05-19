package backend.Order;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "orders")

public class Order implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    @Column(name = "customer_id")
    private int customer_id;

    @Column(name = "shipment_id")
    private int shipmentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description")
    private String description;

    @Column(name = "shipping_addr")
    private String shippingAddr;
    
    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    
    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "discount")
    private double discount;

    public Order() {}
    public Order(int customer_id, int shipmentId, double amount, String description, String shippingAddr, String orderStatus, double discount) {
        this.customer_id = customer_id;
        this.shipmentId = shipmentId;
        this.amount = amount;
        this.description = description;
        this.shippingAddr = shippingAddr;
        this.orderStatus = orderStatus;
        this.discount = discount;
    }

    public Integer getOrderId() {
        return this.orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return this.customer_id;
    }
    public void setCustomerId(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getShipmentId() {
        return this.shipmentId;
    }
    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public double getAmount() {
        return this.amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getShippingAddr() {
        return this.shippingAddr;
    }
    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public String getOrderStatus() {
        return this.orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public double getDiscount() {
        return this.discount;
    }
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order.Orders[ orderId=" + orderId + " ]";
    }
    
}
