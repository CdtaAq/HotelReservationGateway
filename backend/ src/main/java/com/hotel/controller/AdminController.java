@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
public class AdminController {

    @Autowired private FeedbackRepository feedbackRepo;
    @Autowired private FeedbackPDFGenerator pdfGenerator;
    @Autowired private EmailService emailService;

    @PostMapping("/send-feedback-pdf")
    public ResponseEntity<?> sendFeedbackPDF(@RequestParam String email) {
        try {
            List<Feedback> feedbackList = feedbackRepo.findAll();
            byte[] pdf = pdfGenerator.generateFeedbackReport(feedbackList);
            emailService.sendFeedbackReport(email, pdf);
            return ResponseEntity.ok("PDF sent to " + email);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body("Error sending PDF: " + ex.getMessage());
        }
    }
}
