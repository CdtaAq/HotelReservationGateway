package com.hotel.gateway.config;

import com.hotel.gateway.model.Hotel;
import com.hotel.gateway.model.User;
import com.hotel.gateway.model.Booking;
import com.hotel.gateway.repository.HotelRepository;
import com.hotel.gateway.repository.UserRepository;
import com.hotel.gateway.repository.BookingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedInitialData(
            HotelRepository hotelRepo,
            UserRepository userRepo,
            BookingRepository bookingRepo) {

        return args -> {
            // Seed Hotels
            if (hotelRepo.count() == 0) {
                hotelRepo.save(new Hotel("Grand Palace", "New York", 280.0, 4.5, "https://example.com/hotel1.jpg"));
                hotelRepo.save(new Hotel("Oceanview Resort", "Miami", 180.0, 4.2, "https://example.com/hotel2.jpg"));
                hotelRepo.save(new Hotel("Mountain Lodge", "Denver", 150.0, 4.0, "https://example.com/hotel3.jpg"));
                hotelRepo.save(new Hotel("City Inn", "San Francisco", 220.0, 4.3, "https://example.com/hotel4.jpg"));
            }

            // Seed Users
            if (userRepo.count() == 0) {
                userRepo.save(new User("alice", "password123", "ROLE_USER"));
                userRepo.save(new User("bob", "adminpass", "ROLE_ADMIN"));
            }

            // Seed Bookings
            if (bookingRepo.count() == 0) {
                Hotel hotel = hotelRepo.findAll().get(0);
                User user = userRepo.findByUsername("alice");

                bookingRepo.save(new Booking(hotel, user, LocalDate.now().plusDays(5), 2));
                bookingRepo.save(new Booking(hotel, user, LocalDate.now().plusDays(10), 3));
            }

            System.out.println("✅ Seeded hotels, users, and bookings.");
        };
    }
}
