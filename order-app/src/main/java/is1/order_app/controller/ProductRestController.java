package is1.order_app.controller;
import is1.order_app.entities.ProductDTO;



import org.springframework.http.HttpStatus;

import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Validated

class ProductRestController {
    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(
            @NonNull @RequestBody ProductCreateDTO data
    ) {
        return productService.createProduct(data);
    }

}