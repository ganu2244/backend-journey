package com.ganesh.ticketbooking.repo;

import com.ganesh.ticketbooking.domain.Event;
import java.util.List;

public interface EventRepository {
    void save(Event e);
    Event findById(String id);
    List<Event> findAll();
}
