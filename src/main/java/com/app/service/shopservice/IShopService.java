package com.app.service.shopservice;

import com.app.entity.Shop;
import com.app.entity.User;
import com.app.service.IGeneralService;

public interface IShopService extends IGeneralService<Shop> {
    Iterable<Shop> finAllByUser(User user);
}
