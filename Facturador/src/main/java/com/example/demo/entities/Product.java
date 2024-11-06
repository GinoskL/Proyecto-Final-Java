package com.example.demo.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
@Schema(description = "Entidad que representa un producto", example = "{\"id\":\"00000000-0000-0000-0000-000000000000\",\"name\":\"Producto 1\",\"description\":\"Descripcion del producto 1\",\"price\":10.0,\"stock\":100}")
public class Product {
    @Schema(description = "Id del producto" , example = "1 INT , UUID 00000000-0000-0000-0000-000000000000")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del producto" , example = "Producto 1")
    @Column(nullable = false)
    private String name;

    @Schema(description = "Descripcion del producto" , example = "Descripcion del producto 1")
    @Column
    private String description;

    @Schema(description = "Precio del producto" , example = "10.0")
    @Column(nullable = false)
    private double price;

    @Schema(description = "Stock del producto" , example = "100")
    @Column(nullable = false)
    private int stock;

    public Product(String name, String description, double price, int stock) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
    }

    public Double getPrice() {
        return this.price;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}