package com.challenge.product.exception;

public class ProductNotFoundException extends RuntimeException {

    /**
     * Excepción personalizada la cual se lanza cuando el producto no es encontrado.
     */

    public ProductNotFoundException(Long id) {
        super("El producto con id " + id + " no fue encontrado");
    }
}
