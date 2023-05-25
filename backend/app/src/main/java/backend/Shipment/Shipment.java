package backend.Shipment;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name = "shipments")

public class Shipment implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipmentId;
    
    @Column(name = "order_id")
    private int orderId;
    
    @Column(name = "shipment_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;

    @Column(name = "shipment_status")
    private String shipmentStatus = "";

    public Shipment() {}
    public Shipment(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(Integer shipmentId) {
        this.shipmentId = shipmentId;
    }

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }
    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getShipmentStatus() {
        return shipmentStatus;
    }
    public void setShipmentStatus(String shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    @Override
    public String toString() {
        return "backend.Shipment.Shipments[ shipmentId=" + shipmentId + " ]";
    }
    
}
