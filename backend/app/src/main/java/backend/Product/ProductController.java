package backend.Product;

import java.io.*;
import java.nio.file.*;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(path = "/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    
    @Value("${upload.path}")
    private String uploadPath;

    public List<Product> findAll() {
        return productService.getAllProduct();
    }
    
// Upload Image
    @PostMapping("/upload")
    public void uploadFile(@RequestParam("image") MultipartFile file) throws Exception {
        if (file.isEmpty())
            return;

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Path.of(uploadPath, "image", fileName);

            Files.createDirectories(filePath.getParent());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
    
// Create new product
    @PostMapping("/create")
    public Product createProduct(@RequestBody HashMap<String, String> product_form) throws Exception {
        String name = product_form.get("name");
        double price = Double.parseDouble(product_form.get("price"));
        String description = product_form.get("description");
        String image = product_form.get("image");
        
        String create_date = product_form.get("create_date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(create_date);
        
        String category = product_form.get("category");
        
        Product product = new Product(name, price, description, image, date, category);
        productService.save(product);
        List<Product> productrList = productService.getAllProduct();
        for (Product prod: productrList) 
            if (prod.getName().equals(name)) 
                return prod;
        return null;
    }
    
//Delete seat. User will be redirected to the seat page after successfully deleted seat info
    @DeleteMapping("/remove/{productId}")
    public void removeProduc(@PathVariable("productId") String productId) throws Exception {
        try {
            productService.deleteProduct(Integer.valueOf(productId));
        } catch (Exception e) {
            e.printStackTrace();
        }              
    }
    
// Update Order
    @PostMapping("/edit/{productId}/update")
    public Product updateProduct(@RequestBody HashMap<String, String> product_form, @PathVariable("productId") String productId) throws Exception {
        Integer product_id = Integer.valueOf(productId);
        String name = product_form.get("name");
        double price = Double.parseDouble(product_form.get("price"));
        String description = product_form.get("description");
        String image = product_form.get("image");
        String create_date = product_form.get("create_date");
        String category = product_form.get("category");
        return productService.updateProduct(product_id, name, price, description, image, create_date, category);
    }
}
