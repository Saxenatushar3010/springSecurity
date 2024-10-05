package org.example.controller;

import org.example.dto.AuthRequest;
import org.example.dto.Product;
import org.example.entity.UserInfo;
import org.example.service.JwtService;
import org.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    ProductService productService;

    @Autowired
    JwtService jwtService;

    @Autowired
    AuthenticationManager authManager;  // needed for user authentication

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")  // won't allow user to invoke this method
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public Product getProductById(@PathVariable int id) {
        return productService.getProduct(id);
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody UserInfo userInfo) {
        return productService.register(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));  // check user present in db then only generate the jwt
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        }
        return "Invalid User";
    }

}
