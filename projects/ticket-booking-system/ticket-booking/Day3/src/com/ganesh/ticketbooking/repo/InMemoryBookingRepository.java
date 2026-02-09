package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Booking;
import java.util.*;

public class InMemoryBookingRepository implements BookingRepository {
    private final Map<String, Booking> store = new HashMap<>();

    @Override
    public void save(Booking b) { store.put(b.getId(), b); }

    @Override
    public Booking findById(String id) { return store.get(id); }

    @Override
    public List<Booking> findAll() { return new ArrayList<>(store.values()); }
}
