package backend.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface ProductRepository extends CrudRepository<Product, Integer> {
    @Query(value = "SELECT products.product_id FROM products WHERE products.category = ?1", nativeQuery = true)
    List<String> getProductIdByCategory(String category);
}