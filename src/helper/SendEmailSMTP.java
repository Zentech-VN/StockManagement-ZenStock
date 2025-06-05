package helper;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;

public class SendEmailSMTP {

    public static String getOTP() {
        int min = 100000;
        int max = 999999;
        return Integer.toString((int) ((Math.random() * (max - min)) + min));
    }

    public static void sendOTP(String emailTo, String otp) {
        String username = "ducpntv00273@gmail.com";
        String password = "fqyfunbeuacfyxib";
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
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
            message.setSubject("OTP");
            message.setContent("<div style=\"font-family: Helvetica,Arial,sans-serif;min-width:1000px;overflow:auto;line-height:2\">"
                    + "                <div style=\"margin:50px auto;width:70%;padding:20px 0\">"
                    + "                <div style=\"border-bottom:1px solid #eee\">"
                    + "                    <a href=\"#\" style=\"font-size:1.4em;color: #00466a;text-decoration:none;font-weight:600\">Phần mềm quản lý kho điện thoại</a>"
                    + "                </div>"
                    + "                <p style=\"font-size:1.1em\">Hi,</p>"
                    + "                <p>Cảm ơn vì đã sử dụng phần mềm của Zentech. Sử dụng mã OTP sau để hoàn tất quá trình đổi mật khẩu. Mã chỉ có hiệu lực trong vòng 5 phút</p>"
                    + "                <h2 style=\"background: #00466a;margin: 0 auto;width: max-content;padding: 0 10px;color: #fff;border-radius: 4px;\">" + otp + "</h2>"
                    + "                <p style=\"font-size:0.9em;\">Trân trọng,<br />QA</p>"
                    + "                <hr style=\"border:none;border-top:1px solid #eee\" />"
                    + "                <div style=\"float:right;padding:8px 0;color:#aaa;font-size:0.8em;line-height:1;font-weight:300\">"
                    + "                    <p>Phần mềm quản lý kho điện thoại</p>"
                    + "                    <p>Thủ Dầu Một, Bình Dương</p>"
                    + "                    <p>Việt Nam</p>"
                    + "                </div>"
                    + "                </div>"
                    + "                </div>", "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
