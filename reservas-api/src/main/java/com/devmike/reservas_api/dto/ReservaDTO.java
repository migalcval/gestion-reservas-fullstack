package com.devmike.reservas_api.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ReservaDTO {

    //----- Attributes ------

    private Long id;

    @NotNull(message = "La fecha es obligatoria")
    @FutureOrPresent(message = "La reserva debe ser para hoy o el futuro")
    private LocalDateTime fecha;

    @NotBlank(message = "El nombre del cliente es obligatorio")
    private String cliente;

    //----- Constructors ------

    public ReservaDTO() {
        
    }

    public ReservaDTO(Long id, LocalDateTime fecha, String cliente) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    //----- getters & setters -----

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public LocalDateTime getFecha() { 
        return fecha; 
    }

    public void setFecha(LocalDateTime fecha) { 
        this.fecha = fecha; 
    }

    public String getCliente() { 
        return cliente; 
    }

    public void setCliente(String cliente) { 
        this.cliente = cliente; 
    }

}