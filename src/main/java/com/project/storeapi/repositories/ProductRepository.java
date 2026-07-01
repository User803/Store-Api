package com.project.storeapi.repositories;

import com.project.storeapi.dtos.ProductSummaryDto;
import com.project.storeapi.entities.Category;
import com.project.storeapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);
    List<Product> findByPrice(BigDecimal price);
    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    @Query("select p from Product p join p.categories where p.price between :min and :max order by p.name")
    List<Product> findByPriceBetweenOrderByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.categories.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    List<ProductSummaryDto> findByCategories(Category category);

    // Syntax when returning projection as interface
    @Query("select p.id, p.name from Product p where p.categories = :category")
    List<ProductSummaryDto> findByCategoriesInterface(@Param("category") Category category);

    // Syntax when returning projection as a class
    @Query("select new com.project.storeapi.dtos.ProductSummaryDto(p.id, p.name) from Product p where p.categories = :category")
    List<ProductSummaryDto> findByCategoriesClass(@Param("category") Category category);
}
