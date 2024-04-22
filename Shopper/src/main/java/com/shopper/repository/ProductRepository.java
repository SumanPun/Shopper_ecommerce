package com.shopper.repository;

import com.shopper.entites.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p")
    List<Product> getAllProducts();

    @Query(value = "SELECT p FROM Product p WHERE p.name=?1")
    List<Product> findAllByNameContaining(String title);
}
