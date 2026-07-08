package com.project.storeapi.repositories;

import com.project.storeapi.dtos.IProductSummary;
import com.project.storeapi.dtos.ProductDto;
import com.project.storeapi.dtos.ProductSummaryDto;
import com.project.storeapi.entities.Category;
import com.project.storeapi.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductCriteriaRepository, JpaSpecificationExecutor<Product> {

    List<Product> findByName(String name);
    List<Product> findByPrice(BigDecimal price);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    @Query("select p.id as id, p.name as name, p.price as price from Product p")
    List<IProductSummary> findAllProducts();

    @Query("select new com.project.storeapi.dtos.ProductDto(p.id, p.name, p.price, p.categories.id) from Product p")
    List<ProductDto> findAllProductsDto();

    @Query("select new com.project.storeapi.dtos.ProductDto(p.id, p.name, p.price, p.categories.id) from Product p where p.id= :id")
    Optional<ProductDto> findProductById(@Param("id") Long id);

    @EntityGraph(attributePaths = "categories")
    Page<Product> findAll(Pageable pageable);

    @Query("select p from Product p join p.categories where p.price between :min and :max order by p.name")
    List<Product> findByPriceBetweenOrderByName(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    // Stored Procedure
    @Procedure("findProductsByPrice")
    List<Product> findByProductsByPriceBetweenOrderByName(BigDecimal min, BigDecimal max);

    @Query("select count(*) from Product p where p.price between :min and :max")
    long countProducts(@Param("min") BigDecimal min, @Param("max") BigDecimal max);

    @Modifying
    @Query("update Product p set p.price = :newPrice where p.categories.id = :categoryId")
    void updatePriceByCategory(BigDecimal newPrice, Byte categoryId);

    List<ProductSummaryDto> findByCategories(Category category);

    // Syntax when returning projection as interface
    @Query("select p.id as id, p.name as name, p.price as price from Product p where p.categories = :category")
    List<IProductSummary> findByCategoriesInterface(@Param("category") Category category);

    // Syntax when returning projection as a class
    @Query("select new com.project.storeapi.dtos.ProductDto(p.id, p.name, p.price, p.categories.id) from Product p where p.categories.id = :categoryId")
    List<ProductDto> findByCategoriesClassDto(@Param("categoryId") Byte categoryId);

    // Syntax when returning projection as a class
    @Query("select new com.project.storeapi.dtos.ProductSummaryDto(p.id, p.name) from Product p where p.categories = :category")
    List<ProductSummaryDto> findByCategoriesClass(@Param("category") Category category);

    // Syntax when returning projection as a class
    @Query("select new com.project.storeapi.dtos.ProductDto(p.id, p.name, p.price, p.categories.id) from Product p where p.categories = :category")
    List<ProductDto> findByCategoriesDto(@Param("category") Category category);
}
