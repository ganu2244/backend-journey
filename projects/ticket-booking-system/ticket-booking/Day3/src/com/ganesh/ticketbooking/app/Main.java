package com.ganesh.ticketbooking.app;

import com.ganesh.ticketbooking.repo.BookingRepository;
import com.ganesh.ticketbooking.repo.EventRepository;
import com.ganesh.ticketbooking.repo.FileBookingRepository;
import com.ganesh.ticketbooking.repo.FileEventRepository;
import com.ganesh.ticketbooking.service.BookingService;

import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {

        String base = "C:\\Projects\\backend-journey\\projects\\ticket-booking-system\\ticket-booking\\data";

        EventRepository eventRepo = new FileEventRepository(base + "\\events.csv");
        BookingRepository bookingRepo = new FileBookingRepository(base + "\\bookings.csv");

        BookingService service = new BookingService(eventRepo, bookingRepo);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Ticket Booking ---");
            System.out.println("1) List events");
            System.out.println("1A) Available seats for an event");
            System.out.println("2) Book seat");
            System.out.println("3) Cancel booking");
            System.out.println("4) List bookings");
            System.out.println("0) Exit");
            System.out.print("Choose: ");

            String choice = sc.nextLine().trim();

            try {
                if (choice.equals("1")) {
                    service.listEvents().forEach(System.out::println);

                } else if (choice.equalsIgnoreCase("1A")) {
                    System.out.print("EventId: ");
                    String eventId = sc.nextLine().trim();
                    System.out.println("Available seats: " + service.availableSeats(eventId));
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
