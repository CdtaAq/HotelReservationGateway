@RestController
@RequestMapping("/api/feedback")
@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllFeedback() {
        return ResponseEntity.ok(feedbackService.getAllFeedback());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedback(@PathVariable Long id) {
        feedbackService.deleteFeedback(id);
        return ResponseEntity.ok("Feedback deleted");
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<?> approveFeedback(@PathVariable Long id) {
        feedbackService.approveFeedback(id);
        return ResponseEntity.ok("Feedback approved");
    }
}
