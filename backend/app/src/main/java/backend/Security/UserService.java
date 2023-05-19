package backend.Security;

import java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import backend.Customer.CustomerRepository;

@Service
public class UserService implements UserDetailsService{
    private final backend.Customer.CustomerRepository customerReposiroty;

    public UserService(CustomerRepository customerReposiroty) {
        this.customerReposiroty = customerReposiroty;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        backend.Customer.Customer tmpUser = customerReposiroty.getCustomerByPhone(phone);
        if(tmpUser == null) {
            throw new UsernameNotFoundException("Phone not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("myAuthority");
        return new User(tmpUser.getPhone(), tmpUser.getPassword(), Arrays.asList(authority));
    }
    
}
