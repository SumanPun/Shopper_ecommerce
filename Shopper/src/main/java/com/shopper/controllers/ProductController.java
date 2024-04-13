package com.shopper.controllers;

import com.shopper.dto.ProductDto;
import com.shopper.services.admin.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<ProductDto> createProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto saveProduct = this.productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveProduct);
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> productList = this.productService.getProducts();
        return ResponseEntity.ok(productList);
    }
}