package org.example.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.service.BookingService;
import org.example.model.Booking;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingServlet extends HttpServlet {

    private BookingService service = new BookingService();
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        Map<String, Object> response = new HashMap<>();

        try {
            Map<String, String> input = mapper.readValue(req.getInputStream(), Map.class);
            String action = input.get("action");

            if ("book".equals(action)) {
                String name = input.get("name");
                String date = input.get("date");
                String slot = input.get("slot");

                if (name == null || date == null || slot == null) {
                    response.put("error", "Name, date, and slot are required");
                } else {
                    boolean success = service.bookSlot(name, date, slot);
                    if (success) {
                        response.put("message", "Booking successful");
                    } else {
                        response.put("error", "Slot is already booked");
                    }
                }
            } else if ("view".equals(action)) {
                List<Booking> bookings = service.getAllBookings();
                response.put("bookings", bookings);
            } else if ("cancel".equals(action)) {
                String idStr = input.get("id");
                if (idStr == null) {
                    response.put("error", "Booking ID is required");
                } else {
                    try {
                        int id = Integer.parseInt(idStr);
                        boolean success = service.cancelBooking(id);
                        if (success) {
                            response.put("message", "Booking cancelled successfully");
                        } else {
                            response.put("error", "Booking not found");
                        }
                    } catch (NumberFormatException e) {
                        response.put("error", "Invalid booking ID");
                    }
                }
            } else {
                response.put("error", "Invalid action");
            }
        } catch (Exception e) {
            response.put("error", "An error occurred: " + e.getMessage());
        }

        resp.getWriter().write(mapper.writeValueAsString(response));
    }
}
