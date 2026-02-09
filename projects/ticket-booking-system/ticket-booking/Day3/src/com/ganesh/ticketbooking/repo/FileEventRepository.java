package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Event;

import java.time.LocalDateTime;
import java.util.*;

public class FileEventRepository implements EventRepository {

    private final String path;
    private final Map<String, Event> store = new HashMap<>();

    public FileEventRepository(String path) {
        this.path = path;
        load();
    }

    private void load() {
        List<String> lines = CsvStore.readAllLines(path);
        if (lines.isEmpty()) return;

        // skip header
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.isEmpty()) continue;

            String[] parts = line.split(",", -1);
            String id = parts[0];
            String title = parts[1];
            LocalDateTime dt = LocalDateTime.parse(parts[2]);
            int totalSeats = Integer.parseInt(parts[3]);

            store.put(id, new Event(id, title, dt, totalSeats));
        }
    }

    private void persist() {
        List<String> out = new ArrayList<>();
        out.add("id,title,dateTime,totalSeats");
        for (Event e : store.values()) {
            out.add(e.getId() + "," + escape(e.getTitle()) + "," + e.getDateTime() + "," + e.getTotalSeats());
        }
        CsvStore.writeAllLines(path, out);
    }

    private String escape(String s) {
        return s.replace(",", " ");
    }

    @Override
    public void save(Event e) {
        store.put(e.getId(), e);
        persist();
    }

    @Override
    public Event findById(String id) {
        return store.get(id);
    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(store.values());
    }
}
