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

    @Column(name = "user_id")
    private int userId;

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
    public Order(int userId, int shipmentId, double amount, String description, String shippingAddr, String orderStatus, double discount) {
        this.userId = userId;
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

    public int getUserId() {
        return this.userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
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
    public int hashCode() {
        int hash = 0;
        hash += (orderId != null ? orderId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Order)) {
            return false;
        }
        Order other = (Order) object;
        return !((this.orderId == null && other.orderId != null) || (this.orderId != null && !this.orderId.equals(other.orderId)));
    }

    @Override
    public String toString() {
        return "Order.Orders[ orderId=" + orderId + " ]";
    }
    
}
