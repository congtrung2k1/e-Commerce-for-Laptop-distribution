package Shipment;

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
    private String shipmentStatus;

    public Shipment() {}
    public Shipment(int orderId, Date shipmentDate, String shipmentStatus) {
        this.orderId = orderId;
        this.shipmentDate = shipmentDate;
        this.shipmentStatus = shipmentStatus;
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
    public int hashCode() {
        int hash = 0;
        hash += (shipmentId != null ? shipmentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shipment)) {
            return false;
        }
        Shipment other = (Shipment) object;
        return !((this.shipmentId == null && other.shipmentId != null) || (this.shipmentId != null && !this.shipmentId.equals(other.shipmentId)));
    }

    @Override
    public String toString() {
        return "Shipment.Shipment[ shipmentId=" + shipmentId + " ]";
    }
    
}
