package com.ganesh.ticketbooking.service;

import com.ganesh.ticketbooking.domain.Booking;
import com.ganesh.ticketbooking.domain.BookingStatus;
import com.ganesh.ticketbooking.domain.Event;
import com.ganesh.ticketbooking.repo.BookingRepository;
import com.ganesh.ticketbooking.repo.EventRepository;

import java.util.List;

public class BookingService {
    private final EventRepository eventRepo;
    private final BookingRepository bookingRepo;

    public BookingService(EventRepository eventRepo, BookingRepository bookingRepo) {
        this.eventRepo = eventRepo;
        this.bookingRepo = bookingRepo;
    }

    // Rule: seatNo must be 1..totalSeats and not already booked (CONFIRMED)
    public Booking bookSeat(String bookingId, String userId, String eventId, int seatNo) {
        Event event = eventRepo.findById(eventId);
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("UserId cannot be empty");
        }
        if (event == null) throw new IllegalArgumentException("Event not found: " + eventId);
        if (seatNo < 1 || seatNo > event.getTotalSeats()) throw new IllegalArgumentException("Invalid seat number");

        boolean alreadyBooked = bookingRepo.findAll().stream()
                .anyMatch(b -> b.getEventId().equals(eventId)
                        && b.getSeatNo() == seatNo
                        && b.getStatus() == BookingStatus.CONFIRMED);

        if (alreadyBooked) throw new IllegalArgumentException("Seat already booked");

        Booking booking = new Booking(bookingId, userId, eventId, seatNo);
        bookingRepo.save(booking);
        return booking;
    }

    public void cancelBooking(String bookingId) {
        Booking b = bookingRepo.findById(bookingId);
        if (b == null) throw new IllegalArgumentException("Booking not found: " + bookingId);
        b.cancel();
        bookingRepo.save(b);
    }

    public List<Event> listEvents() {
        return eventRepo.findAll();
    }

    public List<Booking> listBookings() {
        return bookingRepo.findAll();
    }

    public List<Integer> availableSeats(String eventId) {
        Event event = eventRepo.findById(eventId);
        if (event == null) throw new IllegalArgumentException("Event not found: " + eventId);

        boolean[] booked = new boolean[event.getTotalSeats() + 1]; // seat index starts at 1

        for (Booking b : bookingRepo.findAll()) {
            if (b.getEventId().equals(eventId) && b.getStatus() == BookingStatus.CONFIRMED) {
                booked[b.getSeatNo()] = true;
            }
        }

        List<Integer> free = new java.util.ArrayList<>();
        for (int i = 1; i <= event.getTotalSeats(); i++) {
            if (!booked[i]) free.add(i);
        }
        return free;
    }
}