package backend.user;

import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signUp(@RequestBody HashMap<String, String> user_form) {
        String password = user_form.get("password");
        String name = user_form.get("name");
        String phone = user_form.get("phone");
        String email = user_form.get("email");
        String address = user_form.get("address");
        String country = user_form.get("country");
        System.out.println(phone);
        try {
            User user = new User(password, name, phone, email, address, country);
            userService.save(user);
            List<User> userList = userService.getAllUser();
            for (User tmp: userList){
                if (tmp.getPhone().equals(phone))
                    return String.valueOf(tmp.getUserId());
            }
            return "0";
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    @PostMapping("/signin")
    public User signIn(@RequestBody HashMap<String, String> data) {
        String phone = data.get("phone");
        String password = data.get("password");

        List<User> userList = userService.getAllUser();
        for (User user: userList) {
            if ( user.getPhone().equals(phone) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }
    
    
// Get user by user_id
    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable("userId") String userId) throws Exception {
        Integer user_id = Integer.valueOf(userId);
        List<User> userList = userService.getAllUser();
        for (User user: userList) {
            if (user.getUserId() == user_id) {
                return user;
            }
        }
        return null;
    }
    
// Get user information for edit
    @GetMapping("/edit/{userId}")
    public String getUserUpdate(@PathVariable("userId") String userId) throws Exception {
        Integer user_id = Integer.valueOf(userId);
        User user = userService.getUser(user_id);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(user);
    }
    
// Update User
    @PostMapping("/edit/{userId}/update")
    public String updateUser(@RequestBody HashMap<String, String> user_form, @PathVariable("userId") String userId) {
        ObjectMapper jsonMapper = new ObjectMapper();
        
        Integer user_id = Integer.valueOf(userId);
        String name = user_form.get("name");
        String password = user_form.get("password");
        String phone = user_form.get("phone");
        String email = user_form.get("email");
        String address = user_form.get("address");
        String country = user_form.get("country");
        System.out.println(phone);
        try {
            User user = userService.updateUser(user_id, name, password, phone, email, address, country);
            return jsonMapper.writeValueAsString(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "{}";
    }

// Show order_id of user orders 
    @GetMapping("user/orders/{userId}")
    public List<String> getUserOrder(@PathVariable("userId") String userId) throws Exception {
        Integer user_id = Integer.valueOf(userId);
        return userService.getUserOrder(user_id);
    }
}
