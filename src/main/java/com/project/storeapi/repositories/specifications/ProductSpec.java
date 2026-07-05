package com.project.storeapi.repositories.specifications;

import com.project.storeapi.entities.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ProductSpec {

    public static Specification<Product> hasName(String name) {
        return (root, query, cb) -> {
            root.fetch("categories", JoinType.LEFT);
            query.distinct(true);

            return cb.like(root.get("name"), "%" + name + "%");
        };
//        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> {
            root.fetch("categories", JoinType.LEFT);
            query.distinct(true);

            return cb.greaterThanOrEqualTo(root.get("price"), price);
        };
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price));
    }

    public static Specification<Product> hasPriceLessThanOrEqualTo(BigDecimal price) {
        return (root, query, cb) -> {
            root.fetch("categories", JoinType.LEFT);
            query.distinct(true);

            return cb.lessThanOrEqualTo(root.get("price"), price);
        };
//        return ((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), price));
    }
}
