package com.app.controller;

import com.app.entity.*;
import com.app.service.categoryservice.ICategoryService;
import com.app.service.evaluateservice.IEvaluateService;
import com.app.service.orderdetailservice.IOrderDetailService;
import com.app.service.orderservice.IOrderService;
import com.app.service.productservice.IProductService;
import com.app.service.shopservice.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeController {
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

    @Autowired
    private IOrderService orderService;

    @GetMapping("/categories/list")
    public ResponseEntity<Iterable<Category>> showAllCategory() {
        List<Category> categories = (List<Category>) categoryService.findAll();
        if (categories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @GetMapping("/categories/finAllByCategory")
    public ResponseEntity<?> findAllByCategory(@RequestBody String categoryName){
        Optional<Category> category = categoryService.findByName(categoryName);
        System.out.println(category);
        Iterable<Product> products = productService.findAllByCategory(category.get());
        System.out.println(products);

        return new ResponseEntity<>((List<Product>)products,HttpStatus.OK);
    }

    @GetMapping("/categories/find/{id}")
    public ResponseEntity<Category> findCategoryById(@PathVariable Long id) {
        Optional<Category> categoryOptional = categoryService.findById(id);
        if (!categoryOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(categoryOptional.get(), HttpStatus.OK);
    }

    @GetMapping("/products/find/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        if (!productOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productOptional.get(), HttpStatus.OK);
    }
    @GetMapping("/products/list")
    public ResponseEntity<Iterable<Product>> showAllProducts(){
        List<Product> productList = (List<Product>) productService.findAll();
        if(productList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(productList,HttpStatus.OK);
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

    @GetMapping("/orders/list")
    public ResponseEntity<Iterable<Order>>showAllOrder(){
        List<Order>orderList = (List<Order>) orderService.findAll();
        if(orderList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/orders/find/{id}")
    public ResponseEntity<Order>findOrderById(@PathVariable Long id){
        Optional<Order>orderOptional = orderService.findById(id);
        if(!orderOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(orderOptional.get(),HttpStatus.OK);
    }
}

