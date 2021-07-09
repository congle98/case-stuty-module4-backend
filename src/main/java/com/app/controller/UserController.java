package com.app.controller;

import com.app.dto.OrderDto;
import com.app.dto.ProductForm;
import com.app.dto.request.BuyOrderRequest;
import com.app.dto.request.CreateCartRequest;
import com.app.dto.request.CreateCartRequest;
import com.app.dto.SearchDto;
import com.app.dto.request.CreateShopRequest;
import com.app.entity.*;
import com.app.security.MessageResponse;
import com.app.service.categoryservice.ICategoryService;
import com.app.service.evaluateservice.IEvaluateService;
import com.app.service.orderdetailservice.IOrderDetailService;
import com.app.service.orderservice.IOrderService;
import com.app.service.productservice.IProductService;
import com.app.service.shopservice.IShopService;
import com.app.service.userservice.IUserService;
import com.app.utils.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/user")

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

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private AppMapper appMapper;


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
    @GetMapping("/categories/list")
    public ResponseEntity<Iterable<Category>> allCategory() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/products/create")
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductForm productForm){
        Product product = productService.converter(productForm);
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products/edit/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @ModelAttribute ProductForm productForm){
            productForm.setId(id);
            Product product = productService.converter(productForm);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
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
    @GetMapping("/products/list")
    public ResponseEntity<Iterable<Product>> allProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/shops/create")
    public ResponseEntity<Shop>createShop(@RequestBody CreateShopRequest shop){
        Optional<User> user = userService.findByUserName(shop.getUserName());
        Shop newShop = new Shop();
        newShop.setName(shop.getShopName());
        newShop.setUser(user.get());
        shopService.save(newShop);
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
    @GetMapping("/shops/{id}")
    public ResponseEntity<Shop> findByUserId(@PathVariable Long id){
        Optional<Shop> shopOptional = Optional.ofNullable(shopService.findShopByUser_Id(id).get(0));
        if (!shopOptional.isPresent()||shopOptional==null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            return new ResponseEntity<>(shopOptional.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/shops/products/{id}")
    public ResponseEntity<Iterable<Product>> ShopProducts(@PathVariable Long id) {
        return new ResponseEntity<>(productService.findProductByShop(id), HttpStatus.OK);
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



    @PostMapping("/orderdetail/create")
    public ResponseEntity<?> createOD(@RequestBody CreateCartRequest createCartRequest){
        System.out.println(createCartRequest);
        Optional<User> user = userService.findByUserName(createCartRequest.getUserName());
        List<Order> orders = (List<Order>) orderService.findAllByUser(user.get());
        Optional<Product> product = productService.findById(Long.valueOf(createCartRequest.getProductId()));
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProduct(product.get());
        orderDetail.setQuantity(1);
        if(!orders.isEmpty()){
            boolean checkCreateNewOrder = true;
            for (Order o:orders
                 ) {
                if(o.getStatus().equals("Giỏ hàng")){
                    checkCreateNewOrder = false;
                    orderDetail.setOrder(o);
                    orderDetailService.save(orderDetail);
                    Optional<Order> orderRespon = orderService.findById(o.getId());
                    return new ResponseEntity<>(orderRespon.get(),HttpStatus.CREATED);
                }
            }
            if (checkCreateNewOrder==true){
                Order order = orderService.save(createOrder(createCartRequest.getUserName()));
                orderDetail.setOrder(order);
                orderDetailService.save(orderDetail);
                Optional<Order> orderRespon = orderService.findById(order.getId());
                return new ResponseEntity<>(orderRespon.get(),HttpStatus.CREATED);
            }
        }
        else {
            Order order = orderService.save(createOrder(createCartRequest.getUserName()));
            orderDetail.setOrder(order);
            orderDetailService.save(orderDetail);
            Optional<Order> orderRespon = orderService.findById(order.getId());
            return new ResponseEntity<>(orderRespon.get(),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    private Order createOrder(String userName){
        Optional<User> user = userService.findByUserName(userName);
        Order order = new Order();
        order.setUser(user.get());
        order.setAddress("chưa xác định");
        order.setCreateTime(new Date(System.currentTimeMillis()));
        order.setStatus("Giỏ hàng");
        return order;
    }

    @PutMapping("/orderdetail/edit/{id}")
    public ResponseEntity<OrderDetail>editOD(@PathVariable Long id, @RequestBody OrderDetail orderDetail){
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
    public ResponseEntity<OrderDetail>deleteOD(@PathVariable Long id){
        Optional<OrderDetail>optionalOrderDetail = orderDetailService.findById(id);
        if(!optionalOrderDetail.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orderDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/orders/create")
    public ResponseEntity<Order>createOrder(@RequestBody Order order){
        orderService.save(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/orders/edit/{id}")
    public ResponseEntity<Order>updateOrder(@PathVariable Long id, @RequestBody Order order){
        Optional<Order> orderOptional = orderService.findById(id);
        if(!orderOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else {
            order.setId(orderOptional.get().getId());
            orderService.save(order);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/orders/delete/{id}")
    public ResponseEntity<?>deleteOrder(@PathVariable Long id){
        Optional<Order>orderOptional=orderService.findById(id);
        if(orderOptional.get().getStatus().equals("Đã nhận")){
            return new ResponseEntity<>(new MessageResponse("Không thể huy đơn đã nhận"),HttpStatus.NOT_FOUND);
        }
        if(orderOptional.get().getStatus().equals("Đang giao")){
            orderOptional.get().setStatus("Đã huỷ");
           Order order = orderService.save(orderOptional.get());
            return new ResponseEntity<>(new MessageResponse("Huỷ đơn thành công"),HttpStatus.OK);
        }

        return new ResponseEntity<>(new MessageResponse("Không thấy đơn"),HttpStatus.NOT_FOUND);
    }
    @GetMapping("/findOrderByUser/{userName}")
    public ResponseEntity<?> findOrderByUser(@PathVariable String userName){
        Optional<User> user = userService.findByUserName(userName);
        Iterable<Order> orders = orderService.findAllByUser(user.get());
        Order order = new Order();
        for (Order o: orders
             ) {
            if(o.getStatus().equals("Giỏ hàng")){
                order = o;
            }
        }
//        Iterable<OrderDetail> orderDetails = orderDetailService.findAllByOrder(order);
//        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        OrderDto orderDto = appMapper.orderToOrderDTO(order);
        System.out.println(orderDto);
        return new ResponseEntity<>(orderDto,HttpStatus.OK);
    }

    @PutMapping("/orders/buy")
    public ResponseEntity<?> orderBy(@RequestBody BuyOrderRequest buyOrderRequest){
        if(buyOrderRequest.getAddress().equals("")){
            return new ResponseEntity<>(new MessageResponse("phải có địa chỉ mới giao hàng dc chứ"),HttpStatus.NOT_FOUND);
        }
        Optional<Order> order = orderService.findById(buyOrderRequest.getId());
        order.get().setAddress(buyOrderRequest.getAddress());
        order.get().setDescription(buyOrderRequest.getDescription());
        order.get().setStatus("Đang giao");
        orderService.save(order.get());
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/findAllOrderByUser/{userName}")
    public ResponseEntity<?> findAllOrderByUser(@PathVariable String userName){
        List<Order> orders = (List<Order>) orderService.findAllByUser(userService.findByUserName(userName).get());
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order o: orders
             ) {
            if(!o.getStatus().equals("Giỏ hàng")){
                orderDtos.add(appMapper.orderToOrderDTO(o));
            }
        }
        return new ResponseEntity<>(orderDtos,HttpStatus.OK);
    }
    @PutMapping("/orders/changeStatus/{id}")
    public ResponseEntity<?> changeStatusOrder(@PathVariable Long id){
        Optional<Order> order = orderService.findById(id);
        if (order.get().getStatus().equals("Đang giao")){
            order.get().setStatus("Đã nhận");
            orderService.save(order.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/shop/check/{userName}")
    public ResponseEntity<?> checkShopByUser(@PathVariable String userName){
        List<Shop> shop = (List<Shop>) shopService.finAllByUser(userService.findByUserName(userName).get());
        if(shop.isEmpty()){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(shop.get(0),HttpStatus.OK);
        }
    }


    @GetMapping("/search")
    public ResponseEntity<SearchDto> searchProduct(@RequestParam(value = "searchname") String searchname) {
        SearchDto searchDto = new SearchDto();
        searchDto.setProductList((List<Product>) productService.searchByName(searchname));
        searchDto.setShopList((List<Shop>) shopService.searchByName(searchname));
        System.out.println(searchDto);
        System.out.println("searcname:"+searchname);
        return new ResponseEntity<>(searchDto, HttpStatus.OK);
    }
//    @PostMapping("/search")
//    public ResponseEntity<SearchDto> searchProduct(@RequestBody String searchname) {
//        SearchDto searchDto = new SearchDto();
//        searchDto.setProductList((List<Product>) productService.searchByName(searchname));
//        searchDto.setShopList((List<Shop>) shopService.searchByName(searchname));
//        System.out.println(searchDto);
//        System.out.println("searcname:"+searchname);
//        return new ResponseEntity<>(searchDto, HttpStatus.OK);
//   }

}
