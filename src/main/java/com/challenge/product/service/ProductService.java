package com.challenge.product.service;

import com.challenge.product.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interfaz de servicios para la gestión de productos.
 */
@Service
public interface ProductService {

    List<ProductDTO> getAllProducts();
    ProductDTO getProduct(Long id);
    ProductDTO createProduct(ProductDTO product);
    ProductDTO updateProduct(Long id, ProductDTO product);
    void deleteProduct(Long id);
}
