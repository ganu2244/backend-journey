package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Booking;
import com.ganesh.ticketbooking.domain.BookingStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FileBookingRepository implements BookingRepository {

    private final String path;
    private final InMemoryBookingRepository delegate = new InMemoryBookingRepository();

    public FileBookingRepository(String path) {
        this.path = path;
        load();
    }

    private void load() {
        List<String> lines = CsvStore.readAllLines(path);
        if (lines.isEmpty()) return;

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] p = line.split(",", -1);
            String id = p[0];
            String userId = p[1];
            String eventId = p[2];
            int seatNo = Integer.parseInt(p[3]);
            BookingStatus status = BookingStatus.valueOf(p[4]);
            LocalDateTime createdAt = LocalDateTime.parse(p[5]);

            Booking b = new Booking(id, userId, eventId, seatNo);
            if (status == BookingStatus.CANCELLED) b.cancel();

            // overwrite createdAt not possible with current Booking model
            // acceptable for now; we’ll improve it on Day 4

            delegate.save(b);
        }
    }

    private void persist() {
        List<String> out = new ArrayList<>();
        out.add("id,userId,eventId,seatNo,status,createdAt");
        for (Booking b : delegate.findAll()) {
            out.add(b.getId() + "," + b.getUserId() + "," + b.getEventId() + "," + b.getSeatNo() + ","
                    + b.getStatus() + "," + b.getCreatedAt());
        }
        CsvStore.writeAllLines(path, out);
    }

    @Override
    public void save(Booking b) {
        delegate.save(b);
        persist();
    }

    @Override
    public Booking findById(String id) {
        return delegate.findById(id);
    }

    @Override
    public List<Booking> findAll() {
        return delegate.findAll();
    }
}
