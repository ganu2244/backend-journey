package com.ganesh.ticketbooking.app;

import com.ganesh.ticketbooking.domain.Event;
import com.ganesh.ticketbooking.repo.*;
import com.ganesh.ticketbooking.service.BookingService;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        EventRepositories eventRepo = new InMemoryEventRepositories();
        BookingRepository bookingRepo = new InMemoryBookingRepository();
        BookingService service = new BookingService(eventRepo, bookingRepo);

        // seed 2 events
        eventRepo.save(new Event("E1", "Movie Night", LocalDateTime.now().plusDays(1), 10));
        eventRepo.save(new Event("E2", "Standup Comedy", LocalDateTime.now().plusDays(2), 5));

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Ticket Booking ---");
            System.out.println("1) List events");
            System.out.println("2) Book seat");
            System.out.println("3) Cancel booking");
            System.out.println("4) List bookings");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            try {
                if (choice.equals("1")) {
                    service.listEvents().forEach(System.out::println);
                } else if (choice.equals("2")) {
                    System.out.print("UserId: ");
                    String userId = sc.nextLine().trim();
                    System.out.print("EventId: ");
                    String eventId = sc.nextLine().trim();
                    System.out.print("SeatNo: ");
                    int seatNo = Integer.parseInt(sc.nextLine().trim());

                    String bookingId = UUID.randomUUID().toString();
                    System.out.println("Booked: " + service.bookSeat(bookingId, userId, eventId, seatNo));
                } else if (choice.equals("3")) {
                    System.out.print("BookingId: ");
                    String bookingId = sc.nextLine().trim();
                    service.cancelBooking(bookingId);
                    System.out.println("Cancelled.");
                } else if (choice.equals("4")) {
                    service.listBookings().forEach(System.out::println);
                } else if (choice.equals("0")) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        sc.close();
        System.out.println("Bye!");
    }
}
