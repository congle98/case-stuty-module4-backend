package com.app.service.shopservice;

import com.app.entity.Shop;
import com.app.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void save(Shop shop) {
        shopRepository.save(shop);
    }

    @Override
    public void remove(Long id) {
        shopRepository.deleteById(id);
    }
}
