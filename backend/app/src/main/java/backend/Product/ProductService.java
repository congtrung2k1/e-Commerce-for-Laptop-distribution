package backend.Product;

import java.util.*;

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
    
    public Product getProductByProductId(Integer product_id) throws Exception {
        Optional<Product> tmpProduct = productRepository.findById(product_id);
        if (tmpProduct.isPresent()) {
            return tmpProduct.get();
        }
        else {
            throw new Exception("Product with id: " + product_id + " not found");
        }
    }

    public List<Product> getProductByCategory(String category) throws Exception {
        List<Product> res = new ArrayList<>();
        List<String> listProductId = (List<String>) productRepository.getProductIdByCategory(category);
        for (String product_id: listProductId) {
            Optional<Product> tmpProduct = productRepository.findById(Integer.valueOf(product_id));
            if (tmpProduct.isPresent()) {
                res.add(tmpProduct.get());
            } else {
                throw new Exception("Product with id: " + product_id + " not found");
            }
        }
        return res;
    }
    
    public void save (Product order) {
        productRepository.save(order);
    }
    
    public void deleteProduct(Integer product_id) throws Exception {
        Optional<Product> tmpSeat = productRepository.findById(product_id);
        if (tmpSeat.isPresent()) {
            productRepository.deleteById(product_id);
        } else {
            throw new Exception("Seat with id: " + product_id + " not found");
        }
    }
    
    public Product updateProduct(Integer product_id, String name, double price, String description, String image, String create_date, String category) throws Exception{
        productRepository.updateProduct(product_id, name, price, description, image, create_date, category);
        return getProductByProductId(product_id);
    }
}
