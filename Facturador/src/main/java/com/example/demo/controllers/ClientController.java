package com.example.demo.controllers;

import com.example.demo.entities.Client;
import com.example.demo.services.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    @Operation(summary = "Get all clients" , description = "Get all clients")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Iterable<Client>> getAll() {
        Iterable<Client> clients = service.getClient();
        return ResponseEntity.ok(clients);
    }

    @Operation(summary = "Get by id client" , description = "Get a client by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Optional<Client>> getById(@PathVariable UUID id) {
        Optional<Client> client = service.getById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Create Client", description = "Create a new client")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> create(@RequestBody Client client) {
        try {
            Client newClient = service.save(client);
            return ResponseEntity.ok(newClient);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }
    @Operation(summary = "Update Client" , description = "Update a client")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @PutMapping(value = "/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Client> update(@PathVariable UUID id, @RequestBody Client updatedClient) {
        Optional<Client> existingClient = service.getById(id);
        if (existingClient.isPresent()) {
            updatedClient.setId(id);
            Client client = service.save(updatedClient);
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Delete client", description = "Delete a client by id")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "404", description = "Not Found")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @ApiResponse(responseCode = "409", description = "Conflict")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}