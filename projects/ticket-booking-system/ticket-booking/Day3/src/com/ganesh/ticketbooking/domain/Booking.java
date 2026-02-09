package com.ganesh.ticketbooking.domain;

import java.time.LocalDateTime;

public class Booking {
    private final String id;
    private final String userId;
    private final String eventId;
    private final int seatNo;
    private BookingStatus status;
    private final LocalDateTime createdAt;

    public Booking(String id, String userId, String eventId, int seatNo) {
        this.id = id;
        this.userId = userId;
        this.eventId = eventId;
        this.seatNo = seatNo;
        this.status = BookingStatus.CONFIRMED;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getEventId() { return eventId; }
    public int getSeatNo() { return seatNo; }
    public BookingStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void cancel() { this.status = BookingStatus.CANCELLED; }

    @Override
    public String toString() {
        return "Booking{id='" + id + "', userId='" + userId + "', eventId='" + eventId + "', seatNo=" + seatNo +
                ", status=" + status + ", createdAt=" + createdAt + "}";
    }
}
