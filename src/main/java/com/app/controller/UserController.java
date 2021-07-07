package com.app.controller;

import com.app.entity.Category;
import com.app.entity.Product;
import com.app.entity.Shop;
import com.app.service.categoryservice.ICategoryService;
import com.app.service.productservice.IProductService;
import com.app.service.shopservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopService shopService;


    @ModelAttribute("categories")
    public Iterable<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("shops")
    public Iterable<Shop>shops(){
        return shopService.findAll();
    }


    @Autowired
    private IProductService productService;

    @GetMapping("categories/list")
    public ResponseEntity<Iterable<Category>> showAllCategory(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        if(categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("/categories/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/categories/create")
    public ResponseEntity createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("/categories/edit/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id,@RequestBody Category category ){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            category.setId(categoryOptional.get().getId());
            categoryService.save(category);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("categories/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("products/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
