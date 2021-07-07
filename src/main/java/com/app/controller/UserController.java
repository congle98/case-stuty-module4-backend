package com.app.controller;

import com.app.entity.Product;
import com.app.service.IProductService;
import com.app.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private IProductService productService;
    @GetMapping("")
    public ModelAndView getAllProductsPage() {
        ModelAndView modelAndView = new ModelAndView("/test/index");
        return modelAndView;
    }
    @GetMapping("findAllProduct")
    public ResponseEntity<List<Product>> findAllSubject(){
        return new ResponseEntity<>((List<Product>) productService.findAll(), HttpStatus.OK);
    }


}
