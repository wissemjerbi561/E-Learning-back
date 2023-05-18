package com.membre.membre.Services;

import com.membre.membre.Entities.Member;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSenderObj;

    public EmailService(JavaMailSender mailSenderObj) {
        this.mailSenderObj = mailSenderObj;
    }


    public void sendmail(@RequestBody Member member) {
        final String emailToRecipient = member.getEmail();
        final String emailSubject = "Suceesfully Registration";
        final String emailMessage1 = "<html> <body> <p>Dear Sir/Madam,</p><p>You have succesfully Registered with our Services"
                + "<br><br>"
                + "<table border='1' width='300px' style='text-align:center;font-size:20px;color:red;'><tr> <td colspan='3'>"
                + "</td></tr>"
                + "<tr><td>Name</td><td>" + member.getFirstName() + "</td></tr>"
                +"<tr><td>Email</td><td>" + member.getEmail() + "</td></tr>"
                +"<tr><td>Password</td><td>" + member.getPassword() + "</td></tr>"
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
