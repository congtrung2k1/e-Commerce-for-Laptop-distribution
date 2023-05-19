package backend.Customer;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService; 

// Get customer by customer_id
    @GetMapping("/{customerId}")
    public Customer getCustomer(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        List<Customer> customerList = customerService.getAllCustomer();
        for (Customer customer: customerList) {
            if (customer.getCustomerId() == customer_id) {
                return customer;
            }
        }
        return null;
    }
    
// Get customer information for edit
    @GetMapping("/edit/{customerId}")
    public Customer getCustomerUpdate(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        return customerService.getCustomer(customer_id);
    }
    
// Update Customer
    @PostMapping("/edit/{customerId}/update")
    public Customer updateCustomer(@RequestBody HashMap<String, String> customer_form, @PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        String name = customer_form.get("name");
        String password = customer_form.get("password");
        String phone = customer_form.get("phone");
        String email = customer_form.get("email");
        String address = customer_form.get("address");
        String country = customer_form.get("country");
        System.out.println(phone);
        return customerService.updateCustomer(customer_id, name, password, phone, email, address, country);
    }

// Show order_id of customer orders 
    @GetMapping("/orders/{customerId}")
    public List<String> getCustomerOrder(@PathVariable("customerId") String customerId) throws Exception {
        Integer customer_id = Integer.valueOf(customerId);
        return customerService.getCustomerOrder(customer_id);
    }
}
