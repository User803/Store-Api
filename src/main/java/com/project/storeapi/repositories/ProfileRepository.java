package com.project.storeapi.repositories;

import com.project.storeapi.dtos.IUserSummary;
import com.project.storeapi.entities.Profile;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    @EntityGraph(attributePaths = "user")
    List<Profile> findByLoyaltyPointsGreaterThan(int loyaltyPoints);

    @Query("select p from Profile p where p.loyaltyPoints > :loyaltyPoints order by p.user.email")
    @EntityGraph(attributePaths = "user")
    List<Profile> findLoyalProfiles(@Param("loyaltyPoints") int loyaltyPoints);
}
