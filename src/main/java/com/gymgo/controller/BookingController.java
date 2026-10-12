
package com.gymgo.controller;

import com.gymgo.model.Booking;
import com.gymgo.repository.BookingRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
    "http://localhost:5173",
    "http://localhost:5175",
    "http://localhost:8080"
})
public class BookingController {

    private final BookingRepository bookingRepository;

    public BookingController(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @PostMapping
    public ResponseEntity<?> createBooking(
            @RequestBody Booking booking) {

        if (booking.getMemberName() == null
                || booking.getMemberName().trim().isEmpty()
                || booking.getMemberEmail() == null
                || booking.getMemberEmail().trim().isEmpty()
                || booking.getClassId() == null) {

            return ResponseEntity.badRequest()
                    .body("Please provide name, email, and class ID.");
        }

        try {
            booking.setMemberName(booking.getMemberName().trim());
            booking.setMemberEmail(booking.getMemberEmail().trim());

            Booking savedBooking = bookingRepository.save(booking);

            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(savedBooking);

        } catch (Exception e) {
            e.printStackTrace();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Could not save booking: " + e.getMessage());
        }
    }
}
