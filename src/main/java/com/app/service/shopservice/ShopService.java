package com.app.service.shopservice;

import com.app.entity.Shop;
import com.app.entity.User;
import com.app.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ShopService implements IShopService{
    @Autowired
    private ShopRepository shopRepository;
    @Override
    public Iterable<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Optional<Shop> findById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public Shop save(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public void remove(Long id) {
        shopRepository.deleteById(id);
    }

    @Override
    public Iterable<Shop> finAllByUser(User user) {
        return shopRepository.findAllByUser(user);
    }
    @Override
    public Iterable<Shop> searchByName(String name){
        return shopRepository.findByNameContaining(name);
    }
    @Override
    public List<Shop> findShopByUser_Id(Long id) {
        return shopRepository.findShopByUser_Id(id);
    }
}
