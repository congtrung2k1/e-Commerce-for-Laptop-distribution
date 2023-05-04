package Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    public List<Product> getAllProduct() {
        List<Product> allProduct = (List<Product>) productRepository.findAll();
        return allProduct;
    }
}
