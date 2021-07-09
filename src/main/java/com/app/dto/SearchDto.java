package com.app.dto;

import com.app.entity.Product;
import com.app.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


public class SearchDto {
    private List<Product> productList;
    private List<Shop> shopList;

    public SearchDto() {
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Shop> getShopList() {
        return shopList;
    }

    public void setShopList(List<Shop> shopList) {
        this.shopList = shopList;
    }

    @Override
    public String toString() {
        return "SearchDto{" +
                "productList=" + productList +
                ", shopList=" + shopList +
                '}';
    }
}
