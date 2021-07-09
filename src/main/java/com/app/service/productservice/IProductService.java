package com.app.service.productservice;

import com.app.entity.Category;
import com.app.entity.Product;
import com.app.service.IGeneralService;

import java.util.List;

public interface IProductService extends IGeneralService<Product> {
    Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> searchByName(String name);
    Iterable<Product> findProductByShop(Long id);

}
