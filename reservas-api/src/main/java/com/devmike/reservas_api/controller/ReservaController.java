package com.devmike.reservas_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devmike.reservas_api.dto.ReservaDTO;
import com.devmike.reservas_api.service.ReservaService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:5173")

public class ReservaController {

    //--------------------------- Attributes ---------------------------

    private final ReservaService reservaService;

    //--------------------------- Constructor ---------------------------

    public ReservaController(ReservaService reservaService) {
        this.reservaService= reservaService;
    }

    //--------------------------- Methods ---------------------------

    //----- POST RESERVA -----
    @PostMapping
    public ReservaDTO crear(@Valid @RequestBody ReservaDTO reserva) {
        return reservaService.postReserva(reserva);
    }

    //----- GET ALL RESERVAS -----
    @GetMapping
    public List<ReservaDTO> getAllReservas() {
        return reservaService.getAllReservas();
    }

    //----- GET RESERVA BY ID -----
    @GetMapping("/{id}")
    public ReservaDTO getReservaById(@PathVariable Long id) {
        return reservaService.getReservaById(id);
    }

    //----- DELETE ALL RESERVAS -----
    @DeleteMapping
    public void deleteAllReservas() {
        reservaService.deleteAllReservas();
    }
    
    //----- DELETE RESERVA BY ID -----
    @DeleteMapping("/{id}")
    public void deleteReservaById(@PathVariable Long id) {
        reservaService.deleteReservaById(id);
    }

    //----- PUT RESERVA BY ID -----
    @PutMapping("/{id}")
    public ReservaDTO putReservaById(@PathVariable Long id, @Valid @RequestBody ReservaDTO reserva) {
        return reservaService.putReservaById(id, reserva);
    }
}
