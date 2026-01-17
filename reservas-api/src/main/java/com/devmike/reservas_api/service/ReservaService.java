package com.devmike.reservas_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.devmike.reservas_api.dto.ReservaDTO;
import com.devmike.reservas_api.exception.NotIdReserva;
import com.devmike.reservas_api.exception.ReservaDuplicadaException;
import com.devmike.reservas_api.model.Reserva;
import com.devmike.reservas_api.repository.ReservaRepository;

@Service

public class ReservaService {

    //--------------------------- Attributes ---------------------------

    private final ReservaRepository reservaRepository;

    //--------------------------- Constructor ---------------------------

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    //--------------------------- Methods ---------------------------

    //----- POST RESERVA -----
    public ReservaDTO postReserva(ReservaDTO reservaDTO) {
        
        Reserva reserva = mapToEntity(reservaDTO);
        int hora = reserva.getFecha().getHour();

        //validacion 1
        if (hora <8 || hora >21) {
            throw new IllegalArgumentException("La reserva debe ser entre las 8:00 y las 21:00 horas");
        }

        int minutos = reserva.getFecha().getMinute();
        if (minutos !=0 && minutos !=30) {
            throw new IllegalArgumentException("La reserva debe ser cada 30min");
        }

        //validacion 3
        if (reservaRepository.findByFecha(reserva.getFecha()).isPresent()) {
        throw new ReservaDuplicadaException("Ya existe una reserva para esa fecha y hora: " + reserva.getFecha());
        }

        Reserva reservaGuardada = reservaRepository.save(reserva);
        return mapToDTO(reservaGuardada);
    }

    //----- GET ALL RESERVAS -----
    public List<ReservaDTO> getAllReservas() {
        return reservaRepository.findAll()
                                .stream()
                                .map(this::mapToDTO)
                                .toList();
    }

    //----- GET RESERVAs BY ID -----
    public ReservaDTO getReservaById(Long id) {
        return reservaRepository.findById(id)
                                .map(this::mapToDTO)
                                .orElseThrow(() -> new NotIdReserva("No existe una reserva con ID: " + id));
    }

    //---- DELETE ALL RESERVAS -----
    public void deleteAllReservas() {
        if (getAllReservas().isEmpty()) {
            throw new IllegalArgumentException("No hay reservas para eliminar");
        }
        reservaRepository.deleteAll();
    }

    //---- DELETE RESERVA BY ID -----
    public void deleteReservaById(Long id) {
        if (reservaRepository.findById(id).isEmpty()) {
            throw new NotIdReserva("No existe una reserva con ID: " + id);
        }
        reservaRepository.deleteById(id);
    }

    //---- PUT RESERVA BY ID -----
    public ReservaDTO putReservaById(Long id, ReservaDTO reserva) {

        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new NotIdReserva("No existe una reserva con ID: " + id));

        if (!reserva.getFecha().equals(reservaExistente.getFecha())) {
            
            if (reservaRepository.findByFecha(reserva.getFecha()).isPresent()) {
                throw new ReservaDuplicadaException("Esa fecha ya está ocupada por otro cliente");
            }
        }

        reservaExistente.setCliente(reserva.getCliente());
        reservaExistente.setFecha(reserva.getFecha());

        Reserva reservaGuardada = reservaRepository.save(reservaExistente);
        return mapToDTO(reservaGuardada);
    }

    //-------- Mappers ---------

    private Reserva mapToEntity(ReservaDTO dto) {
        Reserva reserva = new Reserva();
        reserva.setId(dto.getId()); 
        reserva.setFecha(dto.getFecha());
        reserva.setCliente(dto.getCliente());
        return reserva;
    }

    private ReservaDTO mapToDTO(Reserva reserva) {
        return new ReservaDTO(
            reserva.getId(),
            reserva.getFecha(),
            reserva.getCliente()
        );
    }


}
