package backend.Customer;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "permission")

public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    
    @Column(name = "permission")
    private String permission;

    public Permission() {}
    public Permission(Integer roleId) {
        this.roleId = roleId;
        if (roleId == 0) 
            // 0 Seller, has permission to VIEW order, VIEW products, and MAKE shipments.
            this.permission = "view_make";
        else 
            // !0 Customer, has permission to VIEW order, VIEW products, and CREATE order.
            this.permission = "view_create";
    }

    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = (roleId >= 0 && roleId <= 1) ? roleId : 1;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (roleId != null ? roleId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Permission)) {
            return false;
        }
        Permission other = (Permission) object;
        return !((this.roleId == null && other.roleId != null) || (this.roleId != null && !this.roleId.equals(other.roleId)));
    }

    @Override
    public String toString() {
        return "user.Permission[ roleId=" + roleId + " ]";
    }
}
