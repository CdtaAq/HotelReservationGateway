@Autowired
private PdfEmailUtil pdfEmailUtil;

public Booking createBooking(Booking booking) {
    Booking saved = bookingRepository.save(booking);
    try {
        pdfEmailUtil.sendBookingConfirmation(saved);
    } catch (Exception e) {
        System.out.println("Email failed: " + e.getMessage());
    }
    return saved;
}
