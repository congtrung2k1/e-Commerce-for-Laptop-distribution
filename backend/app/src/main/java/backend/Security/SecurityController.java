package backend.Security;

import backend.Customer.Customer;
import backend.Customer.CustomerDTO;
import backend.Customer.CustomerRepository;
import backend.Customer.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class SecurityController {
    @Autowired
    private final JWTUtility jwtUtility;
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final UserService userService;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private CustomerService customerService; 
    

    public SecurityController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserService userService, CustomerRepository customerRepository) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.customerRepository = customerRepository;
    }
    
    @PostMapping("/signup")
    public String signUp(@RequestBody HashMap<String, String> customer_form) {
        ObjectMapper jsonMapper = new ObjectMapper();

        String name = customer_form.get("name");
        String phone = customer_form.get("phone");
        String password = customer_form.get("password");
        String email = customer_form.get("email");
        String address = customer_form.get("address");
        String country = customer_form.get("country");        
        System.out.println(phone);
        try {
            List<Customer> customerList = customerService.getAllCustomer();
            for (Customer customer: customerList) {
                if ( customer.getPhone().equals(phone)) {
                    return "{\"errorMessage\":\"Phone is used\"}";
                }
            }
            Customer customer = new Customer(name, password, phone, email, address, country);
            customerRepository.save(customer);
            return jsonMapper.writeValueAsString(customer);
        } catch (Exception e) {
            System.out.println(e);
            return "{\"errorMessage\":\"Internal error\"}";
        }
    }   

//    @PostMapping("/signin")
//    public Customer signIn(@RequestBody HashMap<String, String> data) {
//        String phone = data.get("phone");
//        String password = data.get("password");
//
//        List<Customer> customerList = customerService.getAllCustomer();
//        for (Customer customer: customerList) {
//            if ( customer.getPhone().equals(phone) && customer.getPassword().equals(password)) {
//                return customer;
//            }
//        }
//        return null;
//    }
   
    @PostMapping("/authenticate")
    public CustomerDTO authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("invalid", e);
        }
        UserDetails userDetails =  userService.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);
        final String id = String.valueOf(customerRepository.getCustomerByPhone(userDetails.getUsername()).getCustomerId());
        return new CustomerDTO(userDetails.getUsername(), id, token);
    }
}
