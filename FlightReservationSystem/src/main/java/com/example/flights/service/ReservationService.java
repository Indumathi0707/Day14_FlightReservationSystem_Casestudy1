package com.example.flights.service;

import com.example.flights.entity.Flight;
import com.example.flights.entity.Reservation;
import com.example.flights.exception.FlightNotFoundException;
import com.example.flights.exception.NotEnoughSeatsException;
import com.example.flights.repository.FlightRepository;
import com.example.flights.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepo;
    private final FlightRepository flightRepo;

    public ReservationService(ReservationRepository reservationRepo, FlightRepository flightRepo) {
        this.reservationRepo = reservationRepo;
        this.flightRepo = flightRepo;
    }

    public Reservation makeReservation(Long flightId, Reservation reservation) {
        Flight flight = flightRepo.findById(flightId)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + flightId));

        if (reservation.getSeatsBooked() > flight.getSeatsAvailable()) {
            throw new NotEnoughSeatsException("Not enough seats available");
        }

        flight.setSeatsAvailable(flight.getSeatsAvailable() - reservation.getSeatsBooked());
        reservation.setFlight(flight);
        reservation.setReservedAt(LocalDateTime.now());

        flightRepo.save(flight);
        return reservationRepo.save(reservation);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepo.findAll();
    }

    public List<Reservation> getReservationsByFlight(Long flightId) {
        return reservationRepo.findByFlightId(flightId);
    }

    public void cancelReservation(Long id) {
        Reservation reservation = reservationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        Flight flight = reservation.getFlight();
        flight.setSeatsAvailable(flight.getSeatsAvailable() + reservation.getSeatsBooked());

        reservationRepo.delete(reservation);
        flightRepo.save(flight);
    }
}
