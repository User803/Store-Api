package com.project.storeapi.repositories;

import com.project.storeapi.entities.Address;
import com.project.storeapi.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Integer> {
}
