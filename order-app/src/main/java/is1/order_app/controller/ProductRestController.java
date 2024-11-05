package is1.order_app.controller;


import is1.order_app.dto.ProductDTO;
import is1.order_app.dto.StockChangeDTO;
import is1.order_app.dto.ProductViewDTO;
import is1.order_app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@Validated

class ProductRestController {
    private final ProductService productService;

    public ProductRestController(ProductService productService) { this.productService = productService; }

    @PostMapping("/admin")
    public ResponseEntity<ProductViewDTO> createProduct(@NonNull @RequestBody ProductDTO request) {
        try {
            return ResponseEntity.ok(productService.createProduct(request));
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/admin/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        try{
            productService.deleteProduct(productId);
            return ResponseEntity.ok().build();
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/admin/{productId}/stock")
    public ResponseEntity<ProductViewDTO> updateStock(@PathVariable Long productId, @RequestBody StockChangeDTO request) {
        try{
            return ResponseEntity.ok(productService.updateProductStock(productId, request));
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
}