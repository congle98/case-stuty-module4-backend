package com.app.controller;

import com.app.entity.Category;
import com.app.service.categoryservice.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("api/buyer/categories")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/list")
    public ResponseEntity<Iterable<Category>> showAllCategory(){
        List<Category> categories = (List<Category>) categoryService.findAll();
        if(categories.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id){
        Optional<Category> categoryOptional = categoryService.findById(id);
        if(!categoryOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping("edit/{id}")
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
