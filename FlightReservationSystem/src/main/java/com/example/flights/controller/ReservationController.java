package com.example.flights.controller;

import com.example.flights.entity.Reservation;
import com.example.flights.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/{flightId}")
    public ResponseEntity<Reservation> makeReservation(
            @PathVariable Long flightId,
            @RequestBody Reservation reservation) {
        return ResponseEntity.ok(reservationService.makeReservation(flightId, reservation));
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> getAllReservations() {
        return ResponseEntity.ok(reservationService.getAllReservations());
    }

    @GetMapping("/flight/{flightId}")
    public ResponseEntity<List<Reservation>> getReservationsByFlight(@PathVariable Long flightId) {
        return ResponseEntity.ok(reservationService.getReservationsByFlight(flightId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelReservation(@PathVariable Long id) {
        reservationService.cancelReservation(id);
        return ResponseEntity.noContent().build();
    }
}
