package com.product_service.service;

import com.product_service.dto.ProductDto;
import com.product_service.entity.Product;

import java.util.List;

public interface ProductService {
    List<ProductDto> searchProducts(String keyword);
}
