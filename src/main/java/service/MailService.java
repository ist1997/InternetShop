package service;

import dao.CodeDao;
import org.apache.log4j.Logger;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailService {

    private static final Logger logger = Logger.getLogger(MailService.class);

    private static final CodeDao codeDao = new CodeDao();

    public String sendMail(String email) {
        final String username = "matesttest22@gmail.com";
        final String password = "Test12345-";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            String code = String.valueOf(RandomCode.generateCode());
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject("Once code");
            message.setText("Your code: " + code);

            Transport.send(message);
            return code;
        } catch (MessagingException e) {
            logger.error("Can`t connect to from-address");
            return "";
        }
    }
}
