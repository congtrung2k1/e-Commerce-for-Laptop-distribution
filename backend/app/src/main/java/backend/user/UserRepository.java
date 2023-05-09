package backend.user;
 
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT users.user_id FROM users", nativeQuery = true)
    List<String> getUser();

    @Query(value = "SELECT orders.order_id FROM orders WHERE orders.user_id = ?1", nativeQuery = true)
    List<String> getUserOrder(Integer user_id);

    @Modifying
    @Query(value = "UPDATE users SET name = ?2, password = ?3, phone = ?4, email = ?5, address = ?6, country = ?7 WHERE user_id = ?1", nativeQuery = true)
    void updateUser(Integer user_id, String name, String password, String phone, String email, String address, String country);
    
    User findUserByUserId(Integer user_id);
}