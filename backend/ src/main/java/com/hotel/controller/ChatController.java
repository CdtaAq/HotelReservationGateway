@RestController
@RequestMapping("/api/chat")
public class ChatController {

    @Autowired
    private HotelRepository hotelRepo;

    @Autowired
    private OpenAiChatClient openAiChatClient; // Spring AI Bean

    @PostMapping
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> req) {
        String userMsg = req.get("message");

        // Step 1: Prompt AI to extract criteria
        String prompt = """
        Extract hotel search preferences from this text. Return JSON like:
        { "location": "", "minPrice": 0, "maxPrice": 0, "minRating": 0 }

        Message: %s
        """.formatted(userMsg);

        String aiResponse = openAiChatClient.call(prompt); // returns structured JSON
        ObjectMapper mapper = new ObjectMapper();
        HotelSearchCriteria criteria;
        try {
            criteria = mapper.readValue(aiResponse, HotelSearchCriteria.class);
        } catch (Exception e) {
            return ResponseEntity.ok(Map.of("content", "Sorry, I couldn’t understand your request."));
        }

        // Step 2: Query DB with extracted filters
        List<Hotel> matched = hotelRepo.findByFilters(
                criteria.getLocation(), criteria.getMinPrice(), criteria.getMaxPrice(), criteria.getMinRating());

        // Step 3: Format hotel cards
        if (matched.isEmpty())
            return ResponseEntity.ok(Map.of("content", "No hotels matched your criteria. Try again."));

        StringBuilder html = new StringBuilder();
        html.append("<div class='hotel-grid'>");
        for (Hotel h : matched) {
            html.append("""
            <div class='hotel-card'>
              <img src='%s' alt='Hotel Image'>
              <h3>%s</h3>
              <p>Location: %s</p>
              <p>Price: $%s/night</p>
              <p>Rating: ⭐%s</p>
              <a href='/book/%s'>Book Now</a>
            </div>
            """.formatted(h.getImageUrl(), h.getName(), h.getLocation(), h.getPrice(), h.getRating(), h.getId()));
        }
        html.append("</div>");

        return ResponseEntity.ok(Map.of("content", html.toString()));
    }
}
