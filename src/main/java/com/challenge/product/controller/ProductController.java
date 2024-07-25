package com.challenge.product.controller;

import com.challenge.product.dto.ProductDTO;
import com.challenge.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de productos.
 * Proporciona endpoints para las operaciones CRUD de productos.
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * Obtener todos los productos.
     * @return lista de todos los productos.
     */
    @GetMapping
    public List<ProductDTO> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Obtener producto por id.
     * @param id el ID del producto.
     * @return el producto del respectivo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        return ResponseEntity.ok().body(productService.getProduct(id));
    }

    /**
     * Crear nuevo producto.
     * @param productDTO request del producto a crear.
     * @return el producto creado.
     */
    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDTO));
    }

    /**
     * Actualizar un producto existente.
     * @param id del producto a actualizar.
     * @param productDTO request del producto a actualizar.
     * @return el producto actualizado.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        return ResponseEntity.ok(productService.updateProduct(id,productDTO));
    }

    /**
     * Eliminar producto por ID.
     * @param id el ID del producto a eliminar .
     * @return un mensaje de exito al eliminar el producto.
     */
    @DeleteMapping("/remove/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("¡Producto eliminado exitosamente!");
    }
}
