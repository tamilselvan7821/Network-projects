package org.example.model;

public class Booking {
    private int id;
    String name;
    String date;
    String slot;

    public Booking(int id, String name, String date, String slot) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.slot = slot;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getSlot() {
        return slot;
    }

    @Override
    public String toString() {
        return "Booking ID: " + id + " - " + name + " on " + date + " at " + slot;
    }
}
