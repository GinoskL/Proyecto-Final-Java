package com.example.demo.services;

import com.example.demo.entities.Client;
import com.example.demo.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    public Client save(Client client) {
        return repository.save(client);
    }

    public List<Client> getClient() {
        return repository.findAll();
    }

    public Optional<Client> getById(UUID id) {
        return repository.findById(id);
    }

    public void deleteById(UUID id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente no encontrado con ID: " + id);
        }
    }

    public Optional<Client> getByDni(String dni) {
        return repository.findByDni(dni);  // Método de búsqueda por DNI
    }

    public Optional<Client> getByMail(String mail) {
        return repository.findByMail(mail);
    }

    public Optional<Client> findById(UUID id) {
        return repository.findById(id);
    }

    public Optional<Client> findByDni(String dni) {
        return repository.findByDni(dni); // Método de búsqueda por DNI
    }
}
