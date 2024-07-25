package com.challenge.product.service;

import com.challenge.product.dto.ProductDTO;
import com.challenge.product.exception.ProductNotFoundException;
import com.challenge.product.model.Product;
import com.challenge.product.repository.ProductRepository;
import com.challenge.product.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        Product manzana = Product.builder()
                .id(123L)
                .name("Manzana")
                .price(2000.0)
                .build();
        Product leche = Product.builder()
                .id(124L)
                .name("Leche")
                .price(3000.0)
                .build();
        Product pan = Product.builder()
                .id(125L)
                .name("Pan")
                .price(500.0)
                .build();

        List<Product> products = List.of(manzana, leche, pan);

        Mockito.when(productRepository.save(manzana)).thenReturn(manzana);
        Mockito.when(productRepository.findAll()).thenReturn(products);
        Mockito.when(productRepository.findById(pan.getId())).thenReturn(Optional.of(pan));

        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    public void whenValidGetProductThenReturnProduct() {
        ProductDTO product = productService.getProduct(125L);
        Assertions.assertEquals(125L, product.getId());
        Assertions.assertEquals("Pan", product.getName());
        Assertions.assertEquals(500.0, product.getPrice());
    }

    @Test
    public void whenInvalidGetProductThenThrowException() {
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.getProduct(465L));
    }

    @Test
    public void whenValidGetAllProductsThenReturnAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        Assertions.assertEquals(3, products.size());
    }
}
