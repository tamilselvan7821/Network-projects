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

            // 1️⃣ Build HTML properly first
            String htmlOwner = """
            <div style="font-family:Arial">
                <h2>📩 New Portfolio Contact</h2>
                <p><b>From:</b> %s</p>
                <p><b>Subject:</b> %s</p>
                <p><b>Message:</b><br>%s</p>
            </div>
        """.formatted(to, subject, message);

            String ownerMail = """
        {
          "sender": {
            "name": "Tamilselvan Portfolio",
            "email": "%s"
          },
          "to": [
            {
              "email": "%s"
            }
          ],
          "subject": "New Portfolio Contact - %s",
          "htmlContent": "%s"
        }
        """.formatted(USERMAIL, USERMAIL, subject, escapeJson(htmlOwner));

            HttpRequest ownerRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                    .header("api-key", API_KEY)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(ownerMail))
                    .build();

            HttpResponse<String> ownerResponse =
                    client.send(ownerRequest, HttpResponse.BodyHandlers.ofString());

            // 2️⃣ USER AUTO REPLY
            String htmlUser = """
            <div style="font-family:Arial">
                <h2>Thanks for contacting me 🙌</h2>
                <p>Hi %s,</p>
                <p>I received your message and will reply soon.</p>
                <br>
                <p>— Tamilselvan</p>
            </div>
        """.formatted(to);

            String userMail = """
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
          "htmlContent": "%s"
        }
        """.formatted(USERMAIL, to, escapeJson(htmlUser));

            HttpRequest userRequest = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.brevo.com/v3/smtp/email"))
                    .header("api-key", API_KEY)
                    .header("content-type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(userMail))
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
    private String escapeJson(String text) {
        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "");
    }
}
