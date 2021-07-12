package com.app.repository;

import com.app.entity.Category;
import com.app.entity.Product;
import com.app.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Iterable<Product> findAllByCategory(Category category);
    List<Product> findByNameContaining(String name);
    @Query(nativeQuery = true,value = "select * from products where shop_id = ?")
    Iterable<Product> findProductByShop_Id(Long id);

    Iterable<Product> findProductByName(String name);


}