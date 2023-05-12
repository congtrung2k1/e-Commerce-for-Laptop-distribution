package backend.Security;

import backend.user.User;
import backend.user.UserDTO;
import backend.user.UserService;
import backend.user.UserRepository;

import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class SecurityController {
    private final JWTUtility jwtUtility;
    private final AuthenticationManager authenticationManager;
    
    private final UserConnect userConnect;
    private UserService userService;
    private final UserRepository userRepository;

    public SecurityController(JWTUtility jwtUtility, AuthenticationManager authenticationManager, UserConnect userConnect, UserRepository userRepository) {
        this.jwtUtility = jwtUtility;
        this.authenticationManager = authenticationManager;
        this.userConnect = userConnect;
        this.userRepository = userRepository;
    }
    
    @PostMapping("/signup")
    public String signUp(@RequestBody HashMap<String, String> user_form) {
        ObjectMapper jsonMapper = new ObjectMapper();

        String name = user_form.get("name");
        String phone = user_form.get("phone");
        String password = user_form.get("password");
        String email = user_form.get("email");
        String address = user_form.get("address");
        String country = user_form.get("country");
        System.out.println(phone);
        try {
            User user = new User(name, password, phone, email, address, country);
            userRepository.save(user);
            return jsonMapper.writeValueAsString(user);
        } catch (Exception e) {
            System.out.println(e);
            return "{\"errorMessage\":\"Phone is used\"}";
        }
    }   

    @PostMapping("/authenticate")
    public UserDTO authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("invalid", e);
        }
        UserDetails userDetails =  userConnect.loadUserByUsername(jwtRequest.getUsername());

        final String token = jwtUtility.generateToken(userDetails);
        final String id = String.valueOf(userRepository.getUserByPhone(userDetails.getUsername()).getUserId());
        return new UserDTO(userDetails.getUsername(), id, token);
    }
}
