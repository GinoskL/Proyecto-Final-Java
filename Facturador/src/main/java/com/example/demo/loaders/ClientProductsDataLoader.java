package com.example.demo.loaders;

import com.example.demo.entities.Client;
import com.example.demo.entities.Product;
import com.example.demo.services.ClientService;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ClientProductsDataLoader implements CommandLineRunner {

    private final ClientService clientService;
    private final ProductService productService;

    @Autowired
    public ClientProductsDataLoader(ClientService clientService, ProductService productService) {
        this.clientService = clientService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
        // Crear clientes utilizando un HashMap
        Map<String, Client> clients = new HashMap<>();
        clients.put("client1", new Client(UUID.fromString("04b6905f-36b2-4931-b695-2942b14631ea"), "12345678", "John", "Doe",
                "john.doe@example.com", "123-456-7890"));
        clients.put("client2", new Client(UUID.randomUUID(), "87654321", "Jane", "Smith", "jane.smith@example.com", "098-765-4321"));
        clients.put("client3", new Client(UUID.randomUUID(), "11223344", "Alice", "Johnson", "alice.j@example.com", "321-654-9870"));
        clients.put("client4", new Client(UUID.randomUUID(), "44332211", "Bob", "Brown", "bob.brown@example.com", "654-321-0987"));

        // Guardar cada cliente en la base de datos si no está presente
        for (Client client : clients.values()) {
            if (clientService.findByDni(client.getDni()).isEmpty()) {
                clientService.save(client);
            }
        }

        // Crear productos utilizando un HashMap
        Map<String, Product> products = new HashMap<>();
        products.put("product1", new Product("Laptop", "High-performance laptop for work and gaming", 1200.00, 10));
        products.put("product2", new Product("Smartphone", "Latest model smartphone with advanced features", 800.00, 20));
        products.put("product3", new Product("Headphones", "Noise-cancelling wireless headphones", 150.00, 30));
        products.put("product4", new Product("Keyboard", "Mechanical keyboard with RGB lighting", 70.00, 15));
        products.put("product5", new Product("Monitor", "27-inch 4K Ultra HD monitor", 300.00, 8));

        // Guardar cada producto en la base de datos si no está presente
        for (Product product : products.values()) {
            if (productService.findByName(product.getName()).isEmpty()) {
                productService.save(product);
            }
        }
    }
}
