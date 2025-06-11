public List<Booking> getBookingsByUsername(String username) {
    Employee user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    return bookingRepository.findByUser(user);
}
