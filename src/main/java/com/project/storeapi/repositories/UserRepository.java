package com.project.storeapi.repositories;

import com.project.storeapi.dtos.IUserSummary;
import com.project.storeapi.entities.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"tags", "addresses"})
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"addresses", "profile"})
    @Query("select distinct u from User u")
    List<User> findAllWithAddresses();

    @Query("select u.id as id, u.email as email from User u where u.profile.loyaltyPoints > :loyaltyPoints order by u.email")
    List<IUserSummary> findLoyalUsersProjection(@Param("loyaltyPoints") int loyaltyPoints);

    @Query("""
            select distinct u
            from User u
            left join fetch u.addresses
            left join fetch u.profile
    """)
    List<User> findAllWithAddressesProjection();
}
