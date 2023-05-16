package backend.Product;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    
// Get product by product_id
    @GetMapping("/{productId}")
    public String getProductByProductId(@PathVariable("productId") String productId) throws Exception {
        Integer product_id = Integer.valueOf(productId);        
        Product product = productService.getProductByProductId(product_id);
        ObjectMapper jsonMapper = new ObjectMapper();
        return jsonMapper.writeValueAsString(product);
    }
    
// Get product by product_id
    @GetMapping("/category/{category}")
    public List<String> getProductByCategory(@PathVariable("name") String category) throws Exception {
        List<String> result = new ArrayList<>();
        List<Product> productList = productService.getProductByCategory(category);
        for (Product product: productList) {
            ObjectMapper tmp = new ObjectMapper();
            result.add(tmp.writeValueAsString(product));
        }
        return result;
    } 
}
