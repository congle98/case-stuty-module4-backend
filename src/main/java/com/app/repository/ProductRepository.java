package com.app.repository;

import com.app.entity.Category;
import com.app.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findProductByName(String name);
}