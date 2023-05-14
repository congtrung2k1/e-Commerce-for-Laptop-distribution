package backend.Customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> getAllCustomer() {
        List<Customer> allCustomer = (List<Customer>) customerRepository.findAll();
        return allCustomer;
    }
    
    public Customer getCustomer(int customer_id) throws Exception {
        Optional<Customer> tmpCustomer = customerRepository.findById(customer_id);
        if (tmpCustomer.isPresent()) {
            return tmpCustomer.get();
        } else {
            throw new Exception("Customer with id: " + customer_id + " not found");
        }
    }

    public void save (Customer customer) {
        customerRepository.save(customer);
    }
    
    public void deleteCustomer(int customer_id) throws Exception {
        Optional<Customer> tmpCustomer = customerRepository.findById(customer_id);
        if (tmpCustomer.isPresent()) {
            customerRepository.deleteById(customer_id);
        } else {
            throw new Exception("Customer with id: " + customer_id + " not found");
        }
    }
    
    public String hasCustomer(int customer_id) throws Exception {
        Optional <Customer> tmpCustomer = customerRepository.findById(customer_id);
        if (tmpCustomer.isPresent()) {
            return "Available";
        } else {
            throw new Exception("Customer with id:" + customer_id + " not found");
        }
    }
    
    public Customer updateCustomer(Integer customer_id, String name, String password, String phone, String email, String address, String country) throws Exception {
        customerRepository.updateCustomer(customer_id, name, password, phone, email, address, country);
        return getCustomer(customer_id);
    }
    
    public List<String> getCustomerOrder(Integer customer_id){
        return customerRepository.getCustomerOrder(customer_id);
    }

    public String getAddrByCustomerId(Integer customer_id) throws Exception {
        Optional<Customer> tmpCustomer = customerRepository.findById(customer_id);
        if (tmpCustomer.isPresent()) {
            return tmpCustomer.get().getAddress();
        } else {
            throw new Exception("Customer with id: " + customer_id + " not found");
        }
    }
}
