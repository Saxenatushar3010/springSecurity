package org.example.controller;

import org.example.dto.Product;
import org.example.entity.UserInfo;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductService productService;

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome this endpoint is not secure";
    }
    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  // won't allow user to invoke this method
    public List<Product> getAllProducts(){
        return productService.getProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id){
        return productService.getProduct(id);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo userInfo){
        return productService.register(userInfo);
    }
}
