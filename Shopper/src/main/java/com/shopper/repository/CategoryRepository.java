package com.shopper.repository;

import com.shopper.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "SELECT c FROM Category c")
    List getAllCategory();

    @Query(value = "SELECT c FROM Category c WHERE c.id=?1")
    Optional<Category> findById(long id);
}
