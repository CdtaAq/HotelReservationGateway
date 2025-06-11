import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendFeedbackReport(String toEmail, byte[] pdfContent) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject("Hotel Feedback Report");
        helper.setText("Please find the attached feedback report.");

        helper.addAttachment("feedback_report.pdf", new ByteArrayResource(pdfContent));

        mailSender.send(message);
    }
}
