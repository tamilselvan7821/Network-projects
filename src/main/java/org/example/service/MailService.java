package org.example.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class MailService {

    private final static String password = System.getenv("PASSWORD"); // Replace with actual app password
    private final static String myEmail = System.getenv("MAIL"); // Replace with actual email

    public String sendMail(String to, String subject, String text) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");

        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmail, password);
            }
        });
        try {
// 1️⃣ Mail to YOU
            java.net.InetAddress addr =
                    java.net.InetAddress.getByName("smtp.gmail.com");

            System.out.println("SMTP IP = " + addr.getHostAddress());

            Message adminMsg = new MimeMessage(session);

            adminMsg.setFrom(new InternetAddress(myEmail));
            adminMsg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(myEmail));

            adminMsg.setReplyTo(new Address[]{
                    new InternetAddress(to) // user's email
            });

            adminMsg.setSubject("Portfolio Contact: " + subject);

            adminMsg.setText(
                    "From: " + to + "\n\n" +
                            text
            );

            Transport.send(adminMsg);


// 2️⃣ AUTO REPLY TO USER
            Message userMsg = new MimeMessage(session);

            userMsg.setFrom(new InternetAddress(myEmail));
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
