package com.app.service.productservice;

import com.app.dto.ProductForm;
import com.app.entity.Category;
import com.app.entity.Product;
import com.app.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;
@Service
public class ProductService implements IProductService {
    @Autowired
    Environment environment;
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> searchByName(String name) {
        return productRepository.findByNameContaining(name);
    }

    @Override
    public Iterable<Product> findProductByShop(Long id) {
        return productRepository.findProductByShop_Id(id);
    }

    @Override
    public Product converter(ProductForm productForm) {

        MultipartFile multipartFile = productForm.getAvatar();
        String fileName = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("upload.path").toString();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(),new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setId(productForm.getId());
        product.setShop(product.getShop());
        product.setCategory(productForm.getCategory());
        product.setName(productForm.getName());
        product.setPrice(productForm.getPrice());
        product.setSalePrice(productForm.getSalePrice());
        product.setAvatar(fileName);
        return product;
    }

    @Override
    public Iterable<Product> findAllProductByName(String name) {
        return productRepository.findProductByName(name);
    }

}
