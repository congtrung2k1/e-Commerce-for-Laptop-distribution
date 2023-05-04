package Order;

import java.io.Serializable;
import java.math.BigDecimal;
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
    private BigDecimal amount;

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
    private BigDecimal discount;

    public Order() {}
    public Order(int userId, int shipmentId, BigDecimal amount, String description, String shippingAddr, Date orderDate, String orderStatus, BigDecimal discount) {
        this.userId = userId;
        this.shipmentId = shipmentId;
        this.amount = amount;
        this.description = description;
        this.shippingAddr = shippingAddr;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.discount = discount;
    }

    public Integer getOrderId() {
        return orderId;
    }
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getShippingAddr() {
        return shippingAddr;
    }
    public void setShippingAddr(String shippingAddr) {
        this.shippingAddr = shippingAddr;
    }

    public Date getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
    public void setDiscount(BigDecimal discount) {
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
