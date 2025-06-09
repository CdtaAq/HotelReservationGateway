package com.hotel.gateway.util;

import com.hotel.gateway.model.Booking;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;

@Component
public class PdfEmailUtil {

    @Autowired
    private JavaMailSender mailSender;

    public void sendBookingConfirmation(Booking booking) throws MessagingException {
        byte[] pdfBytes = generateBookingPdf(booking);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(booking.getUser().getEmail());
        helper.setSubject("Hotel Booking Confirmation - " + booking.getHotel().getName());
        helper.setText("Thank you for booking! Please find your booking confirmation attached.");

        InputStreamSource attachment = new ByteArrayResource(pdfBytes);
        helper.addAttachment("booking-confirmation.pdf", attachment);

        mailSender.send(message);
    }

    private byte[] generateBookingPdf(Booking booking) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(baos);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            document.add(new Paragraph("Hotel Booking Confirmation"));
            document.add(new Paragraph("Booking ID: " + booking.getId()));
            document.add(new Paragraph("Hotel: " + booking.getHotel().getName()));
            document.add(new Paragraph("User: " + booking.getUser().getName()));
            document.add(new Paragraph("Check-in: " + booking.getCheckIn().format(DateTimeFormatter.ISO_DATE)));
            document.add(new Paragraph("Check-out: " + booking.getCheckOut().format(DateTimeFormatter.ISO_DATE)));
            document.add(new Paragraph("Guests: " + booking.getGuests()));
            document.add(new Paragraph("Total Price: $" + booking.getTotalPrice()));

            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return baos.toByteArray();
    }
}
