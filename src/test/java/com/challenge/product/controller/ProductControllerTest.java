package com.challenge.product.controller;

import com.challenge.product.dto.ProductDTO;
import com.challenge.product.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;


/**
 * Test unitarios para ProductController.
 */
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    /**
     * Test para obtener todos los productos.
     * @throws Exception si un error ocurre.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        ProductDTO product1 = new ProductDTO(1L, "product1", 2000.0);
        ProductDTO product2 = new ProductDTO(2L, "product2", 3000.0);
        List<ProductDTO> products = List.of(product1, product2);

        when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }

    /**
     * Test para obtener un producto por ID.
     * @throws Exception si un error ocurre.
     */
    @Test
    public void testGetProductById() throws Exception {
        ProductDTO product = new ProductDTO(1L, "Product1", 10.0);

        when(productService.getProduct(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk());
    }

    /**
     * Test para crear un producto.
     * @throws Exception si un error ocurre.
     */
    @Test
    public void testCreateProduct() throws Exception {
        ProductDTO createdProduct = new ProductDTO(1L, "NewProduct", 15.0);

        when(productService.createProduct(any(ProductDTO.class))).thenReturn(createdProduct);

        mockMvc.perform(post("/products/create")
                        .contentType("application/json")
                        .content("{\"name\":\"NewProduct\",\"price\":15.0}"))
                .andExpect(status().isCreated());
    }

    /**
     * Test para eliminar un producto.
     * @throws Exception si un error ocurre.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        doNothing().when(productService).deleteProduct(anyLong());

        mockMvc.perform(delete("/products/remove/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("¡Producto eliminado exitosamente!"));
    }
}
