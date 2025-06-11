import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Component
public class FeedbackPDFGenerator {

    public byte[] generateFeedbackReport(List<Feedback> feedbackList) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, baos);

        doc.open();
        doc.add(new Paragraph("Hotel Feedback Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16)));
        doc.add(new Paragraph("Generated: " + new java.util.Date()));
        doc.add(new Paragraph("\n"));

        for (Feedback f : feedbackList) {
            doc.add(new Paragraph("Hotel: " + f.getHotel().getName()));
            doc.add(new Paragraph("User: " + f.getUser().getUsername()));
            doc.add(new Paragraph("Rating: " + f.getRating()));
            doc.add(new Paragraph("Comment: " + f.getComment()));
            doc.add(new Paragraph("Approved: " + (f.isApproved() ? "Yes" : "No")));
            doc.add(new Paragraph("-------------------------------------"));
        }

        doc.close();
        return baos.toByteArray();
    }
}
