package com.example.demo.entities;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "Entidad que representa a un cliente" ,example = "{\"dni\":\"12345678\",\"name\":\"Juan\",\"lastname\":\"Perez\",\"mail\":\"juanperez@gmail.com\",\"telefono\":\"+56987654321\"}")
@Table(name = "client")
public class Client {
    public Client(UUID id) {
        this.id = id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    public Client(String dni, String name, String lastname, String mail, String telefono) {
        this.dni = dni;
        this.name = name;
        this.lastname = lastname;
        this.mail = mail;
        this.telefono = telefono;
    }
    @Schema(description = "Id del cliente" , example = "00000000-0000-0000-0000-000000000000")
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Schema(description = "DNI del cliente" , example = "12345678")
    @Column(nullable = false, unique = true)
    private String dni;


    @Schema(description = "Nombre del cliente" , example = "Juan")
    @Column(nullable = false)
    private String name;

    @Schema(description = "Apellido del cliente" , example = "Perez")
    @Column(nullable = false)
    private String lastname;

    @Schema(description = "Mail del cliente" ,example = "juanperez@gmail.com")
    @Column(nullable = false, unique = true)
    private String mail;

    @Schema(description = "Telefono del cliente" ,example = "+56987654321")
    @Column
    private String telefono;

    public void setName(String name) {
        this.name = name;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setEmail(String email) {
        this.mail = email;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


    public UUID getId() {
        return id;
    }
}