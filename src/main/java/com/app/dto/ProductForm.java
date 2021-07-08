package com.app.dto;

import com.app.entity.Category;
import com.app.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    private Long id;

    private String name;

    private double price;

    private double salePrice;

    private MultipartFile avatar;

    private Category category;

    private Shop shop;

}
