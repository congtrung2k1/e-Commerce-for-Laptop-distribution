package backend.Product;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    public List<Product> findAll() {
        return productService.getAllProduct();
    }

    // Get all product
    @GetMapping("")
    public List<Product> getAllProduct() throws Exception {
        List<Product> result = (List<Product>) productService.findAll();
        return result;
    }
    
// Get product by product_id
    @GetMapping("/{productId}")
    public Product getProductByProductId(@PathVariable("productId") String productId) throws Exception {
        Integer product_id = Integer.valueOf(productId);        
        return productService.getProductByProductId(product_id);
    }
    
// Get product by product_id
    @GetMapping("/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("category") String category) throws Exception {
        return productService.getProductByCategory(category);
    } 
}
