package com.coderhouse.models;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "users")  
@Schema(description = "Modelo de usuarios")
public class User {

    @Schema(description = "ID del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Nombre del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Abel")
    private String nombre;

    @Schema(description = "Apellido del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "Gonzalez")
    private String apellido;

    @Schema(description = "Email del usuario", requiredMode = Schema.RequiredMode.REQUIRED, example = "example@example.com")
    private String email;

    @Schema(description = "Muestra la hora y fecha del registro del usuario")
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // Constructor con parámetros
    public User(String nombre, String apellido, String email, LocalDateTime createdAt) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.createdAt = createdAt;
    }

    
    public Long getId() {
        return id;
    }

  
    public void setId(Long id) {
        this.id = id;
    }
}
