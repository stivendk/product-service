package com.challenge.product.dto;

import com.challenge.product.model.Product;

public class ProductMapper {

    public static ProductDTO toProductDTO(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice());
    }

    public static Product toProduct(ProductDTO productDTO) {
        return new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice());
    }

}
