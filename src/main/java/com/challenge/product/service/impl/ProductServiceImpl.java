package com.challenge.product.service.impl;

import com.challenge.product.dto.ProductDTO;
import com.challenge.product.dto.ProductMapper;
import com.challenge.product.exception.ProductNotFoundException;
import com.challenge.product.model.Product;
import com.challenge.product.repository.ProductRepository;
import com.challenge.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementación de servicios para la gestión de productos.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    /**
     * Obtener todos los productos.
     * @return lista de todos los productos.
     */
    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductMapper::toProductDTO)
                .collect(Collectors.toList());
    }

    /**
     * Obtener un producto por su ID.
     * @param id el ID del producto.
     * @return El producto consultado.
     */
    @Override
    public ProductDTO getProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return ProductMapper.toProductDTO(product);
    }

    /**
     * Crear un nuevo producto.
     * @param product request del producto a crear.
     * @return el producto creado.
     */
    @Override
    public ProductDTO createProduct(ProductDTO product) {
        Product newProduct = ProductMapper.toProduct(product);
        Product savedProduct = productRepository.save(newProduct);
        return ProductMapper.toProductDTO(savedProduct);
    }

    /**
     * Actualizar un producto existente.
     * @param id el ID del producto a actualizar.
     * @param productDTO request del producto a actualizar.
     * @return producto actualizado.
     */
    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product findProduct = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        findProduct.setName(productDTO.getName());
        findProduct.setPrice(productDTO.getPrice());
        Product updatedProduct = productRepository.save(findProduct);
        return ProductMapper.toProductDTO(updatedProduct);
    }

    /**
     * Eliminar un producto por su respectivo ID.
     * @param id ID del producto a eliminar.
     */
    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        productRepository.delete(product);
    }
}
