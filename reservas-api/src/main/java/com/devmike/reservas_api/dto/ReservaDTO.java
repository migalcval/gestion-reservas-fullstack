package com.devmike.reservas_api.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReservaDTO(Long id, @NotNull @FutureOrPresent LocalDateTime fecha, @NotBlank String cliente) {
 

}