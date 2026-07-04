package com.project.storeapi.services;

import com.project.storeapi.entities.Product;
import com.project.storeapi.repositories.ProductRepository;
import com.project.storeapi.repositories.ProfileRepository;
import com.project.storeapi.repositories.UserRepository;
import com.project.storeapi.repositories.specifications.ProductSpec;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional(readOnly=true)
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ProfileRepository profileRepository;


    public UserService(UserRepository userRepository, ProductRepository productRepository, ProfileRepository profileRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.profileRepository = profileRepository;
    }

//    @Transactional
//    public void manageProducts() {
//        var user = userRepository.findById(1L).orElseThrow();
//        var products = productRepository.findAll();
//        products.forEach(user::addFavoriteProduct);
//        userRepository.save(user);
//    }

    public void findProducts() {
        var product = new Product();
        product.setName("product");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        var example = Example.of(product, matcher);

        productRepository.findAll(example)
                    .forEach(System.out::println);
    }

    public void findProductsByCriteria() {
        productRepository.findProductsByCriteria(null, BigDecimal.ONE, BigDecimal.valueOf(5000))
                    .forEach(System.out::println);
    }

    public void findProductsBySpecification(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        Specification<Product> spec = Specification.where(null);

        if (name != null) {
            spec = spec.and(ProductSpec.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpec.hasPriceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpec.hasPriceLessThanOrEqualTo(maxPrice));
        }

        productRepository.findAll(spec)
                .forEach(System.out::println);
    }

    public void findSortedProducts() {
        Sort sort = Sort.by("name")
                .and(Sort.by("price").descending());

        productRepository.findAll(sort)
                .forEach(System.out::println);
    }

    public void findPaginatedProducts(int pageNumber, int size) {
        PageRequest pageRequest = PageRequest.of(pageNumber, size);
        Page<Product> page = productRepository.findAll(pageRequest);

        page.getContent().forEach(System.out::println);

        int totalPages = page.getTotalPages();
        var totalElements = page.getTotalElements();

        System.out.println("Total elements: " + totalElements);
        System.out.println("Total pages: " + totalPages);
    }

    public void findLoyalProfiles() {
        userRepository.findLoyalUsersProjection(30)
                .forEach(profile -> {
                    System.out.println(profile.getId() + " : " + profile.getEmail());
        });
    }
}
