package com.devmike.reservas_api.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devmike.reservas_api.model.Reserva;


public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    
    Optional<Reserva> findByFecha(LocalDateTime fecha);

}
