package is1.order_app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.ProductFilterDTO;
import is1.order_app.dto.StockChangeDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@Validated

class ProductRestController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductRestController.class);
    public ProductRestController(ProductService productService) { this.productService = productService; }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProductViewDTO> createProduct(@NonNull @RequestBody ProductDTO request) {
        try {
            return ResponseEntity.ok(productService.createProduct(request));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{productId}/stock")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStock(@PathVariable Long productId, @RequestBody StockChangeDTO request) {
        try{
            return ResponseEntity.ok(productService.updateProductStock(productId, request));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @PutMapping("/{productId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateAllAtributes(@PathVariable Long productId,@NonNull @RequestBody ProductDTO productDTO) {
        try{
            return ResponseEntity.ok(productService.updateProduct(productId, productDTO));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping
    public ResponseEntity<List<ProductViewDTO>> getAllProducts() {
        try{
            return ResponseEntity.ok(productService.getAllProducts());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductViewDTO> getProduct(@PathVariable Long productId) {
        try{
            return ResponseEntity.ok(productService.getProductById(productId));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("/{productId}/stock")
    public ResponseEntity<?> getStockProduct(@PathVariable Long productId) {
        try{
            return ResponseEntity.ok(productService.getProductById(productId).getStock());
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }
    @GetMapping("/search")
    public ResponseEntity<List<ProductViewDTO>> getProductListFiltered(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) Integer stock,
        @RequestParam(required = false) String brand,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) String extraAtributes

    ) {
        ProductFilterDTO filter = new ProductFilterDTO();
        filter.setName(name);
        filter.setStock(stock);
        filter.setBrand(brand);
        filter.setDescription(description);
        filter.setExtraAtributes(extraAtributes);

        try{
            return ResponseEntity.ok(productService.getProductListFiltered(filter));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
}