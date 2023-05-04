package user;

import Order.Order;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public List<User> getAllUser() {
        List<User> allUser = (List<User>) userRepository.findAll();
        return allUser;
    }
    
    public User getUser(int user_id) throws Exception {
        Optional<User> tmpUser = userRepository.findById(user_id);
        if (tmpUser.isPresent()) {
            return tmpUser.get();
        } else {
            throw new Exception("User with id: " + user_id + " not found");
        }
    }

    public void save (User user) {
        userRepository.save(user);
    }
    
    public void deleteUser(int user_id) throws Exception {
        Optional<User> tmpUser = userRepository.findById(user_id);
        if (tmpUser.isPresent()) {
            userRepository.deleteById(user_id);
        } else {
            throw new Exception("User with id: " + user_id + " not found");
        }
    }
    
    public String hasUser(int user_id) throws Exception {
        Optional <User> tmpUser = userRepository.findById(user_id);
        if (tmpUser.isPresent()) {
            return "Available";
        } else {
            throw new Exception("User with id:" + user_id + " not found");
        }
    }
    
    public User updateUser(Integer user_id, String name, String password, String phone, String email, String address, String country) throws Exception {
        userRepository.updateUser(user_id, name, password, phone, email, address, country);
        return getUser(user_id);
    }
    
    public List<String> getUserOrder(Integer user_id){
        return userRepository.getUserOrder(user_id);
    }
}
