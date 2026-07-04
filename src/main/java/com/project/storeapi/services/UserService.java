package com.project.storeapi.services;

import com.project.storeapi.repositories.ProductRepository;
import com.project.storeapi.repositories.ProfileRepository;
import com.project.storeapi.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        userRepository.findAllWithAddresses()
                .forEach(user -> {
                    System.out.println(user);
                    user.getAddresses().forEach(System.out::println);
        });
    }

    public void findLoyalProfiles() {
        userRepository.findLoyalUsersProjection(30)
                .forEach(profile -> {
                    System.out.println(profile.getId() + " : " + profile.getEmail());
        });
    }
}
