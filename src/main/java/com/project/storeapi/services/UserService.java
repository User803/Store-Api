package com.project.storeapi.services;

import com.project.storeapi.entities.Category;
import com.project.storeapi.repositories.ProductRepository;
import com.project.storeapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

//    @Transactional
//    public void manageProducts() {
//        var user = userRepository.findById(1L).orElseThrow();
//        var products = productRepository.findAll();
//        products.forEach(user::addFavoriteProduct);
//        userRepository.save(user);
//    }

    @Transactional
    public void findProducts() {
        productRepository.findByCategoriesClass(new Category((byte) 2))
                .forEach(System.out::println);
    }
}
