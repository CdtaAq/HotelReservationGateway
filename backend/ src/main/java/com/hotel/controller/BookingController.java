@GetMapping("/my-bookings")
public ResponseEntity<?> getUserBookings(@AuthenticationPrincipal UserDetails userDetails) {
    String username = userDetails.getUsername();
    List<Booking> bookings = bookingService.getBookingsByUsername(username);
    return ResponseEntity.ok(bookings);
}
