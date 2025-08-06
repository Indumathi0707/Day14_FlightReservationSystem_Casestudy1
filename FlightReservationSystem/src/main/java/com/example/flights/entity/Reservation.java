package com.example.flights.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String passengerName;
    private String passengerEmail;
    private int seatsBooked;
    private LocalDateTime reservedAt;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    public Long getId() {
        return id;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public String getPassengerEmail() {
        return passengerEmail;
    }

    public int getSeatsBooked() {
        return seatsBooked;
    }

    public LocalDateTime getReservedAt() {
        return reservedAt;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public void setPassengerEmail(String passengerEmail) {
        this.passengerEmail = passengerEmail;
    }

    public void setSeatsBooked(int seatsBooked) {
        this.seatsBooked = seatsBooked;
    }

    public void setReservedAt(LocalDateTime reservedAt) {
        this.reservedAt = reservedAt;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }
}
