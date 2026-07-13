package com.project.storeapi.controllers;

import com.project.storeapi.dtos.ProductDto;
import com.project.storeapi.dtos.UpdateProductRequest;
import com.project.storeapi.entities.Category;
import com.project.storeapi.entities.Product;
import com.project.storeapi.mappers.ProductMapper;
import com.project.storeapi.repositories.CategoryRepository;
import com.project.storeapi.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final CategoryRepository categoryRepository;

    public ProductController(ProductRepository productRepository, ProductMapper productMapper, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.categoryRepository = categoryRepository;
    }

//    @GetMapping
//    public ResponseEntity<List<IProductSummary>> getAllProducts(
//            @RequestParam(required = false, name = "categoryId") Category categoryId) {
//
//        if (!(categoryId == null)) {
//            return ResponseEntity.ok(productRepository.findByCategoriesInterface(categoryId));
//        }
//
//        return ResponseEntity.ok(productRepository.findAllProducts());
//    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProductsDto(
            @RequestParam(required = false, name = "categoryId") Byte categoryId) {

        if (categoryId != null) {
            return ResponseEntity.ok(productRepository.findByCategoriesClassDto(categoryId));
        }

        return ResponseEntity.ok(productRepository.findAllProductsDto());
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id) {
//        Product product = productRepository.findById(id).orElse(null);
//
//        if (product == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(productRepository.findProductById(id));

        return productRepository.findProductById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        Category category = categoryRepository.findById(productDto.categoryId()).orElse(null);

        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productMapper.toProduct(productDto);
        product.setCategories(category);
        productRepository.save(product);

        return new ResponseEntity<>(productMapper.toProductDto(product), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                                    @RequestBody UpdateProductRequest request) {

        Category category = categoryRepository.findById(request.categoryId()).orElse(null);
        if (category == null) {
            return ResponseEntity.badRequest().build();
        }

        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productMapper.updateProduct(request, product);
        productRepository.save(product);

        return ResponseEntity.ok(productMapper.toProductDto(product));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        Product product = productRepository.findById(id).orElse(null);

        if (product == null) {
            return ResponseEntity.notFound().build();
        }

        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }
}
