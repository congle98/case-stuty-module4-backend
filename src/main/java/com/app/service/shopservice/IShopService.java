package com.app.service.shopservice;

import com.app.entity.Shop;
import com.app.entity.User;
import com.app.service.IGeneralService;

import java.util.List;

public interface IShopService extends IGeneralService<Shop> {
    Iterable<Shop> finAllByUser(User user);
    Iterable<Shop> searchByName(String name);
    List<Shop> findShopByUser_Id(Long id);

}
