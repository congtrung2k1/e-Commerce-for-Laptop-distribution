package backend.Product;

import java.util.List;

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
    @GetMapping("/product/{productId}")
    public Product getProductByProductId(@PathVariable("productId") String productId) throws Exception {
        Integer product_id = Integer.valueOf(productId);
        List<Product> productList = productService.getAllProduct();
        for (Product product: productList) {
            if (product.getProductId() == product_id) {
                return product;
            }
        }
        return null;
    }
    
// Get product by product_id
    @GetMapping("/product/category/{category}")
    public List<Product> getProductByCategory(@PathVariable("name") String category) throws Exception {
        List<Product> productList = productService.getProductByCategory(category);
        return productList;
    } 
}
