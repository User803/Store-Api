package com.project.storeapi.repositories;

import com.project.storeapi.entities.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    public ProductCriteriaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Product> findProductsByCriteria(String name, BigDecimal minPrice, BigDecimal maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
        Root<Product> root = criteriaQuery.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();
        if (name != null) {
            predicates.add(cb.like(root.get("name"), "%" + name + "%"));
        }
        if (minPrice != null) {
            predicates.add(cb.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(cb.lessThanOrEqualTo(root.get("price"), maxPrice));
        }

        criteriaQuery.select(root)
                .where(predicates.toArray(new Predicate[predicates.size()]));

        return entityManager.createQuery(criteriaQuery)
                .getResultList();
    }
}
