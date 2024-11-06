package com.example.demo.services;

import com.example.demo.entities.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public List<Product> getProducts() {
        return repository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + id);
        }
    }

    public void updateProductStock(Product product) {
        System.out.println("Actualizando stock para el producto ID: " + product.getId());
        if (repository.existsById(product.getId())) {
            repository.save(product);
            System.out.println("Stock actualizado para el producto: " + product.getId());
        } else {
            throw new RuntimeException("Producto no encontrado con ID: " + product.getId());
        }
    }

    public Optional<Product> findByName(String name) {
        return repository.findByName(name);
    }
}

