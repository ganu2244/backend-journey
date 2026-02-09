package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Booking;
import java.util.List;

public interface BookingRepository {
    void save(Booking b);
    Booking findById(String id);
    List<Booking> findAll();
}
