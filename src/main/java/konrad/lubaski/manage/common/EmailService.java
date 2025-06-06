package konrad.lubaski.manage.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Service
public class EmailService {

    private final JavaMailSender sender;
    //todo @RestController oraz @Controller
    //value odnosci sie do wartosci w properties
    @Value("${spring.mail.username}")
    private String yourEmail;

    public EmailService(JavaMailSender sender) {
        this.sender = sender;
    }

    //@PostConstruct
    @Async
    public void sendHtmlEmail(String to, String subject, String htmlBody) {
        try {
            MimeMessage mimeMessage = sender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(to);
            helper.setFrom(yourEmail);
            helper.setSubject(subject);
            helper.setText(htmlBody, true);
            sender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void testSend() {
        sendSimpleEmail(yourEmail,
                "zobaczmy teraz",
                "Siema, działam");
    }

    @Async
    public void sendSimpleEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom(yourEmail);
        message.setSubject(subject);
        message.setText(body);
        sender.send(message);
    }
}