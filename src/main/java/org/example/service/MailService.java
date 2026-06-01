package org.example.service;

import jakarta.mail.*;
import jakarta.mail.internet.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class MailService {

    private final static String API_KEY = System.getenv("API_KEY");
    private final static String USERMAIL = System.getenv("USERMAIL");

    public String sendMail(String to, String subject, String message) {

        try {

            HttpClient client = HttpClient.newHttpClient();

            // 1️⃣ MAIL TO YOU (PORTFOLIO OWNER)
            String ownerMail = """
        {
          "sender": {
            "name": "Tamilselvan",
            "email": "%s"
          },
          "to": [
            {
              "email": "%s",
              "name": "Tamilselvan"
            }
          ],
          "subject": "📩 Contact: %s",
          "htmlContent": "<h2>New Contact Request</h2>"
                        + "<p><b>From:</b> %s</p>"
                        + "<p><b>Subject:</b> %s</p>"
                        + "<p><b>Message:</b><br>%s</p>"
        }
        """.formatted(USERMAIL, USERMAIL, subject, to, subject, message);

            HttpRequest ownerRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                    .header("api-key", API_KEY)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(ownerMail))
                    .build();

            HttpResponse<String> ownerResponse =
                    client.send(ownerRequest, HttpResponse.BodyHandlers.ofString());

            // 2️⃣ AUTO REPLY TO USER
            String autoReply = """
        {
          "sender": {
            "name": "Tamilselvan",
            "email": "%s"
          },
          "to": [
            {
              "email": "%s"
            }
          ],
          "subject": "Thanks for contacting me 🙌",
          "htmlContent": "<h2>Hi %s 👋</h2>"
                        + "<p>Thanks for reaching out through my portfolio.</p>"
                        + "<p>I have received your message and will reply soon.</p>"
                        + "<br><p>— Tamilselvan</p>"
        }
        """.formatted(USERMAIL, to, to);

            HttpRequest userRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                    .header("api-key", API_KEY)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(autoReply))
                    .build();

            HttpResponse<String> userResponse =
                    client.send(userRequest, HttpResponse.BodyHandlers.ofString());

            return "OwnerMail: " + ownerResponse.statusCode()
                    + " | UserMail: " + userResponse.statusCode();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
