package com.example.flights.service;

import com.example.flights.entity.Flight;
import com.example.flights.exception.FlightNotFoundException;
import com.example.flights.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightService {

    private final FlightRepository flightRepo;

    public FlightService(FlightRepository flightRepo) {
        this.flightRepo = flightRepo;
    }

    public Flight addFlight(Flight flight) {
        return flightRepo.save(flight);
    }

    public List<Flight> getAllFlights() {
        return flightRepo.findAll();
    }

    public Flight getFlightById(Long id) {
        return flightRepo.findById(id)
                .orElseThrow(() -> new FlightNotFoundException("Flight not found with ID: " + id));
    }

    public Flight updateFlight(Long id, Flight updated) {
        Flight flight = getFlightById(id);
        flight.setOrigin(updated.getOrigin());
        flight.setDestination(updated.getDestination());
        flight.setDepartureTime(updated.getDepartureTime());
        flight.setSeatsAvailable(updated.getSeatsAvailable());
        return flightRepo.save(flight);
    }

    public void deleteFlight(Long id) {
        flightRepo.deleteById(id);
    }
}
