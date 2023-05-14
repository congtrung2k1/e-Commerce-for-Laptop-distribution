package backend.Customer;

public class CustomerDTO {
    public String phone;
    public String userId;
    public String token;

    public CustomerDTO(String phone, String userId, String token) {
        this.phone = phone;
        this.userId = userId;
        this.token = token;
    }

    public String getUsername() {
        return this.phone;
    }
    public void setUsername(String phone) {
        this.phone = phone;
    }

    public String getUserId() {
        return this.userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return this.token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
