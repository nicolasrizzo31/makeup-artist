package com.example.makeup_artist.bookings.dto;

// Add basic validation annotations if desired, e.g., from javax.validation or jakarta.validation
// For simplicity, just fields and getters/setters for now.
public class BookingDTO {
    private String name;
    private String email;
    private String date; // Consider using LocalDate or a specific date format
    private String service;

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getService() { return service; }
    public void setService(String service) { this.service = service; }

    @Override
    public String toString() {
        return "BookingDTO{" +
               "name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", date='" + date + '\'' +
               ", service='" + service + '\'' +
               '}';
    }
}
