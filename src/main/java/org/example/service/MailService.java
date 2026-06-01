package org.example.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailService {

    private final static String password = System.getenv("PASSWORD"); // Replace with actual app password
    private final static String myEmail = System.getenv("MAIL");
    private final static String userMail = System.getenv("USERMAIL");

    public String sendMail(String to, String subject, String text) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp-relay.brevo.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        props.put("mail.smtp.connectiontimeout", "60000");
        props.put("mail.smtp.timeout", "60000");
        props.put("mail.smtp.writetimeout", "60000");
        Session session = Session.getInstance(
                props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication(
                                myEmail,
                                password
                        );
                    }
                }
        );
        try{
            Message message = new MimeMessage(session);

            message.setFrom(
                new InternetAddress(userMail)
            );

            message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(userMail)
            );

            message.setSubject("Contact: " + subject);

            message.setText(
                "From: " + to + "\n\n" +
                        text);

            Transport.send(message);

// 2️⃣ AUTO REPLY TO USER
            Message userMsg = new MimeMessage(session);

            userMsg.setFrom(new InternetAddress(userMail));
            userMsg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            userMsg.setSubject("Thanks for contacting me!");

            userMsg.setText(
                    "Hi,\n\n" +
                            "Thank you for reaching out. I will get back to you soon.\n\n" +
                            "- Tamilselvan"
            );

            Transport.send(userMsg);
            return "Mail Sent Successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Error sending mail: " + e.getMessage();
        }
    }
}
