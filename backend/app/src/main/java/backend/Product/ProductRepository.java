package backend.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "SELECT products.product_id FROM products WHERE products.category = ?1", nativeQuery = true)
    List<String> getProductIdByCategory(String category);
    
    @Modifying
    @Query(value = "UPDATE products SET name = ?2, price = ?3, description = ?4, image = ?5, create_date = ?6, category = ?7 WHERE product_id = ?1", nativeQuery = true)
    void updateProduct(Integer product_id, String name, double price, String description, String image, String create_date, String category);
}