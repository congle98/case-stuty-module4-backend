package com.app.repository;

import com.app.entity.Product;
import com.app.entity.Shop;
import com.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
    Iterable<Shop> findAllByUser(User user);
    List<Shop> findByNameContaining(String name);
    @Query(nativeQuery = true,value = "select * from shops where user_id = ?")
    List<Shop> findShopByUser_Id(Long id);


}
