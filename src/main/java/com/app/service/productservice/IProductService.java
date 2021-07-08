package com.app.service.productservice;

import com.app.dto.ProductForm;
import com.app.entity.Product;
import com.app.service.IGeneralService;

public interface IProductService extends IGeneralService<Product> {
     Product converter(ProductForm productForm);
}
