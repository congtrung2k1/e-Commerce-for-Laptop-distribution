package backend.Security;

import backend.user.*;

import java.util.Arrays;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserConnect implements UserDetailsService{
    private backend.user.UserRepository userRepository;

    public UserConnect(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        backend.user.User tmpUser = userRepository.getUserByPhone(phone);
        if(tmpUser == null) {
            throw new UsernameNotFoundException("Phone not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority("myAuthority");
        return new User(tmpUser.getPhone(), tmpUser.getPassword(), Arrays.asList(authority));
    }
    
}
