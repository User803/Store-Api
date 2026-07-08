package com.project.storeapi.controllers;

import com.project.storeapi.dtos.IProductSummary;
import com.project.storeapi.dtos.ProductDto;
import com.project.storeapi.entities.Category;
import com.project.storeapi.repositories.ProductRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
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
    public ResponseEntity<Optional<ProductDto>> getProduct(@PathVariable Long id) {
        if (productRepository.findProductById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productRepository.findProductById(id));
    }
}
