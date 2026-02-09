package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Event;
import java.util.*;

public class InMemoryEventRepositories implements EventRepositories {
    private final Map<String, Event> store = new HashMap<>();

    @Override
    public void save(Event e) { store.put(e.getId(), e); }

    @Override
    public Event findById(String id) { return store.get(id); }

    @Override
    public List<Event> findAll() { return new ArrayList<>(store.values()); }
}
