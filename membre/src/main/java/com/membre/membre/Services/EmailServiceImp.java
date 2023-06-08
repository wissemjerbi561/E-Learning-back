package com.membre.membre.Services;

import com.membre.membre.Entities.Member;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    @Autowired
    private JavaMailSender mailSenderObj;

    public EmailServiceImp(JavaMailSender mailSenderObj) {
        this.mailSenderObj = mailSenderObj;
    }
    @Override
    public void sendmail(String firstName,String email,String password) {
        final String emailToRecipient = email;
        final String emailSubject = "Suceesfully Registration";
        final String emailMessage1 = "<html> <body> <p>Dear Sir/Madam,</p><p>You have succesfully Registered with our Services"
                + "<br><br>"
                + "<table border='1' width='300px' style='text-align:center;font-size:20px;color:red;'><tr> <td colspan='3'>"
                + "</td></tr>"
                + "<tr><td>Name</td><td>" + firstName + "</td></tr>"
                +"<tr><td>Email</td><td>" + email + "</td></tr>"
                +"<tr><td>Password</td><td>" + password + "</td></tr>"
                +"</table> "
                +"<p>Cordially, the e-learning team</p> </body></html>";

        mailSenderObj.send(new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {

                MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
                mimeMsgHelperObj.setTo(emailToRecipient);

                mimeMsgHelperObj.setFrom("azaiezabdessalem@gmail.com");

                mimeMsgHelperObj.setText(emailMessage1, true);

                mimeMsgHelperObj.setSubject(emailSubject);
            }
        });
    }
}
