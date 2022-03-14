import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EmailService {

    public static boolean sendMail(String to, String subject, String msg, InputStream in) {

        try {
            Properties prop = readPropertiesFile(in);
            prop.put("mail.smtp.host", "smtp.gmail.com");
            prop.put("mail.smtp.port", "587");
            prop.put("mail.smtp.auth", "true");
            prop.put("mail.smtp.starttls.enable", "true"); //TLS
            prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
            prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(prop,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(prop.getProperty("username"), prop.getProperty("password"));
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(prop.getProperty("username")));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

                message.setSubject(subject);
                message.setContent(
                        msg,
                        "text/html");

                Transport.send(message);

                System.out.println("Done");
                return true;

            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }
        return false;
    }

    public static Properties readPropertiesFile(InputStream in) throws IOException {
        Properties prop = null;
        try{
            prop = new Properties();
            prop.load(in);
        } catch(IOException fnfe) {
            fnfe.printStackTrace();
        }
        return prop;
    }
}