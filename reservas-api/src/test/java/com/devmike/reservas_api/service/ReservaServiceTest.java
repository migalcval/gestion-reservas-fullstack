package com.devmike.reservas_api.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devmike.reservas_api.dto.ReservaDTO;
import com.devmike.reservas_api.exception.ReservaDuplicadaException;
import com.devmike.reservas_api.model.Reserva;
import com.devmike.reservas_api.repository.ReservaRepository;

//--------------------------- Test Class --------------------------- PARA EJECUTAR TESTS --> .\mvnw test ---------------------------

@ExtendWith(MockitoExtension.class) // Habilita Mockito
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository; // El repositorio es "falso"

    @InjectMocks
    private ReservaService reservaService; // El servicio es el real que probamos

    @Test
    void testCrearReservaDuplicadaLanzaExcepcion() {
        // 1. GIVEN (Preparación del escenario)
        LocalDateTime fechaConflictiva = LocalDateTime.of(2024, 12, 25, 10, 0);

        // Simulamos que la base de datos YA tiene una reserva en esa fecha
        Reserva reservaExistenteEnBBDD = new Reserva();
        reservaExistenteEnBBDD.setId(1L);
        reservaExistenteEnBBDD.setFecha(fechaConflictiva);
        reservaExistenteEnBBDD.setCliente("Cliente Antiguo");

        // Enseñamos al Mock a mentir: "Si te preguntan por esta fecha, di que SÍ existe"
        when(reservaRepository.findByFecha(fechaConflictiva))
                .thenReturn(Optional.of(reservaExistenteEnBBDD));

        // Preparamos el DTO nuevo que vamos a intentar colar
        ReservaDTO reservaNuevaDTO = new ReservaDTO();
        reservaNuevaDTO.setFecha(fechaConflictiva); // ¡Misma fecha!
        reservaNuevaDTO.setCliente("Cliente Nuevo");

        // 2. WHEN & THEN (Ejecución y Verificación)
        // Esperamos que al llamar a postReserva, explote con nuestra excepción
        assertThrows(ReservaDuplicadaException.class, () -> {
            reservaService.postReserva(reservaNuevaDTO);
        });

        // Verificación extra: Aseguramos que el método .save() NUNCA se llamó
        verify(reservaRepository, never()).save(any());
    }

    @Test
    void testCrearReservaFueraDeHorarioLanzaExcepcion() {
        LocalDateTime fechaMala = LocalDateTime.of(2024, 12, 25, 3, 0); 
    
        ReservaDTO reservaMalaDTO = new ReservaDTO();
        reservaMalaDTO.setFecha(fechaMala);
        reservaMalaDTO.setCliente("angeloto ramales");

        // Esperamos IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.postReserva(reservaMalaDTO);
        });
    }

    @Test
    void testCrearReservaMinutosIncorrectosLanzaExcepcion() {
        LocalDateTime fechaImprecisa = LocalDateTime.of(2024, 12, 25, 10, 15);
    
        ReservaDTO reservaImprecisaDTO = new ReservaDTO();
        reservaImprecisaDTO.setFecha(fechaImprecisa);
        reservaImprecisaDTO.setCliente("Impreciso");

        // Esperamos IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.postReserva(reservaImprecisaDTO);
        });
    }
}