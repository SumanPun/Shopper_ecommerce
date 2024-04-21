package com.shopper.services.admin.impl;

import com.shopper.dto.ProductDto;
import com.shopper.entites.Category;
import com.shopper.entites.Product;
import com.shopper.exceptions.ResourceNotFoundException;
import com.shopper.repository.CategoryRepository;
import com.shopper.repository.ProductRepository;
import com.shopper.services.admin.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public ProductDto createProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());
        product.setDescription(productDto.getDescription());
        Category category = this.categoryRepository.findByProductId(productDto.getCategoryId()).orElseThrow(()-> new ResourceNotFoundException("Category","CategoryId", productDto.getCategoryId()));
        product.setCategory(category);
        return this.productRepository.save(product).getDto();
    }

    public List<ProductDto> getProducts() {
        List<Product> listProducts = this.productRepository.getAllProducts();
        return listProducts.stream().map(Product::getDto).collect(Collectors.toList());
    }

}
