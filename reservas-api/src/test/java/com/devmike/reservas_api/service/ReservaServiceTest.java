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

//--------------------------- Test Class --------------------------- 

@ExtendWith(MockitoExtension.class)
public class ReservaServiceTest {

    @Mock
    private ReservaRepository reservaRepository;

    @InjectMocks
    private ReservaService reservaService;

    @Test
    void testCrearReservaDuplicadaLanzaExcepcion() {
        LocalDateTime fechaConflictiva = LocalDateTime.of(2024, 12, 25, 10, 0);

        Reserva reservaExistenteEnBBDD = new Reserva();
        reservaExistenteEnBBDD.setId(1L);
        reservaExistenteEnBBDD.setFecha(fechaConflictiva);
        reservaExistenteEnBBDD.setCliente("Cliente Antiguo");

        when(reservaRepository.findByFecha(fechaConflictiva))
                .thenReturn(Optional.of(reservaExistenteEnBBDD));

        ReservaDTO reservaNuevaDTO = new ReservaDTO(null, fechaConflictiva, "Cliente Nuevo");

        assertThrows(ReservaDuplicadaException.class, () -> {
            reservaService.postReserva(reservaNuevaDTO);
        });

        verify(reservaRepository, never()).save(any());
    }

    @Test
    void testCrearReservaFueraDeHorarioLanzaExcepcion() {
        LocalDateTime fechaMala = LocalDateTime.of(2024, 12, 25, 3, 0); 
    
        ReservaDTO reservaMalaDTO = new ReservaDTO(null,fechaMala, "angeloto ramales");

        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.postReserva(reservaMalaDTO);
        });
    }

    @Test
    void testCrearReservaMinutosIncorrectosLanzaExcepcion() {
        LocalDateTime fechaImprecisa = LocalDateTime.of(2024, 12, 25, 10, 15);
    
        ReservaDTO reservaImprecisaDTO = new ReservaDTO(null, fechaImprecisa, "Impreciso");

        assertThrows(IllegalArgumentException.class, () -> {
            reservaService.postReserva(reservaImprecisaDTO);
        });
    }
}