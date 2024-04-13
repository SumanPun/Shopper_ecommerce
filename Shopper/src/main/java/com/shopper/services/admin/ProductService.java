package com.shopper.services.admin;

import com.shopper.dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    public ProductDto createProduct(ProductDto productDto) throws IOException;

    public List<ProductDto> getProducts();
}
