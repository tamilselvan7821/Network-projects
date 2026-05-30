package org.example.service;

import org.example.model.Booking;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BookingService {
    private static List<Booking> bookings = new ArrayList<>();
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    public synchronized boolean bookSlot(String name, String date, String slot) {
        if (isSlotAvailable(date, slot)) {
            Booking booking = new Booking(idGenerator.getAndIncrement(), name, date, slot);
            bookings.add(booking);
            return true;
        }
        return false;
    }

    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings);
    }

    public boolean cancelBooking(int id) {
        return bookings.removeIf(b -> b.getId() == id);
    }

    private boolean isSlotAvailable(String date, String slot) {
        for (Booking b : bookings) {
            if (b.getDate().equals(date) && b.getSlot().equals(slot)) {
                return false;
            }
        }
        return true;
    }
}
