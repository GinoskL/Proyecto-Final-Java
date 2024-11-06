package com.example.demo.services;

import com.example.demo.entities.InvoiceItem;
import com.example.demo.entities.Product;
import com.example.demo.repositories.InvoiceItemRepository;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class InvoiceItemsService {

    @Autowired
    private InvoiceItemRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public InvoiceItem save(InvoiceItem item) {
        return repository.save(item);
    }

    public List<InvoiceItem> getAllItems() {
        return repository.findAll();
    }


    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<InvoiceItem> findByInvoiceId(UUID invoiceId) {
        return repository.findByInvoiceId(invoiceId);
    }

    public void deleteById(UUID id) {
        Optional<InvoiceItem> item = repository.findById(id);
        if (item.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Item de factura no encontrado con ID: " + id);
        }
    }
}