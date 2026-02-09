package com.ganesh.ticketbooking.domain;

import java.time.LocalDateTime;

public class Event {
    private final String id;
    private final String title;
    private final LocalDateTime dateTime;
    private final int totalSeats;

    public Event(String id, String title, LocalDateTime dateTime, int totalSeats) {
        this.id = id;
        this.title = title;
        this.dateTime = dateTime;
        this.totalSeats = totalSeats;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public LocalDateTime getDateTime() { return dateTime; }
    public int getTotalSeats() { return totalSeats; }

    @Override
    public String toString() {
        return "Event{id='" + id + "', title='" + title + "', dateTime=" + dateTime + ", totalSeats=" + totalSeats + "}";
    }
}
