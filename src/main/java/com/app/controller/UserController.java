package com.app.controller;

import com.app.entity.*;
import com.app.service.categoryservice.ICategoryService;
import com.app.service.evaluateservice.IEvaluateService;
import com.app.service.orderdetailservice.IOrderDetailService;
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
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IShopService shopService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IEvaluateService evaluateService;

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/categories/list")
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
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
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

    @DeleteMapping("/categories/delete/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            categoryService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/products/list")
    public ResponseEntity<Iterable<Product>> showAllProducts(){
        List<Product> productList = (List<Product>) productService.findAll();
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList,HttpStatus.OK);
    }

    @GetMapping("/products/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(),HttpStatus.OK);
    }

    @PutMapping("/products/edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> productOptional = productService.findById(id);
        if(!productOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            product.setId(productOptional.get().getId());
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            productService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/shops/list")
    public ResponseEntity<Iterable<Shop>>showAllShop(){
        List<Shop> shopList = (List<Shop>) shopService.findAll();
        if(shopList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(shopList,HttpStatus.OK);
    }

    @GetMapping("/shops/find/{id}")
    public ResponseEntity<Shop>findShopById(@PathVariable Long id){
        Optional<Shop>shopOptional= shopService.findById(id);
        if(!shopOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(shopOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/shops/create")
    public ResponseEntity<Shop>createShop(@RequestBody Shop shop){
        shopService.save(shop);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/shops/edit/{id}")
    public ResponseEntity<Shop>editShop(@PathVariable Long id, @RequestBody Shop shop){
        Optional<Shop>shopOptional = shopService.findById(id);
        if(!shopOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            shop.setId(shopOptional.get().getId());
            shopService.save(shop);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/shops/delete/{id}")
    public ResponseEntity<Shop>deleteShop(@PathVariable Long id){
        Optional<Shop> shopOptional = shopService.findById(id);
        if(!shopOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            shopService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/evaluate/list")
    public ResponseEntity<Iterable<Evaluate>>showAllEvaluate(){
        List<Evaluate>evaluateList = (List<Evaluate>) evaluateService.findAll();
        if(evaluateList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(evaluateList,HttpStatus.OK);
    }

    @GetMapping("/evaluate/find/{id}")
    public ResponseEntity<Evaluate>findEvaluateById(@PathVariable Long id){
        Optional<Evaluate>evaluateOptional = evaluateService.findById(id);
        if(!evaluateOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(evaluateOptional.get(),HttpStatus.OK);
    }

    @PostMapping("/evaluate/create")
    public ResponseEntity<Evaluate>createEvaluate(@RequestBody Evaluate evaluate){
        evaluateService.save(evaluate);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/evaluate/edit/{id}")
    public ResponseEntity<Evaluate>updateEvaluate(@PathVariable Long id,@RequestBody Evaluate evaluate){
        Optional<Evaluate>evaluateOptional = evaluateService.findById(id);
        if(!evaluateOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            evaluate.setId(evaluateOptional.get().getId());
            evaluateService.save(evaluate);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    @DeleteMapping("evaluate/delete/{id}")
    public ResponseEntity<Evaluate>deleteEvaluate(@PathVariable Long id){
        Optional<Evaluate>evaluateOptional = evaluateService.findById(id);
        if(!evaluateOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            evaluateService.remove(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/orderdetail/list")
    public ResponseEntity<Iterable<OrderDetail>>showAllOrderDetail(){
        List<OrderDetail> orderDetailList = (List<OrderDetail>) orderDetailService.findAll();
        if(orderDetailList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderDetailList,HttpStatus.OK);
    }

    @GetMapping("/orderdetail/find/{id}")
    public ResponseEntity<OrderDetail>findOrderDetailById(@PathVariable Long id){
        Optional<OrderDetail> optionalOrderDetail = orderDetailService.findById(id);
        if(!optionalOrderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(optionalOrderDetail.get(),HttpStatus.OK);
    }

    @PostMapping("/orderdetail/create")
    public ResponseEntity<OrderDetail> createOD(@RequestBody OrderDetail orderDetail){
        orderDetailService.save(orderDetail);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/orderdetail/edit/{id}")
    private ResponseEntity<OrderDetail>editOD(@PathVariable Long id, @RequestBody OrderDetail orderDetail){
        Optional<OrderDetail>optionalOrderDetail = orderDetailService.findById(id);
        if(!optionalOrderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            orderDetail.setId(optionalOrderDetail.get().getId());
            orderDetailService.save(orderDetail);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/orderdetail/delete/{id}")
    private ResponseEntity<OrderDetail>deleteOD(@PathVariable Long id){
        Optional<OrderDetail>optionalOrderDetail = orderDetailService.findById(id);
        if(!optionalOrderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
