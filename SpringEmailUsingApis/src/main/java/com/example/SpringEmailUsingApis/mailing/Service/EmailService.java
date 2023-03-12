package com.example.SpringEmailUsingApis.mailing.Service;

import com.example.SpringEmailUsingApis.mailing.Models.EmailDetails;
import com.example.SpringEmailUsingApis.mailing.Repositories.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;

@Service
public class EmailService implements EmailRepository {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;


    @Override
    public String sendSimpleMailToMany(EmailDetails emailDetails) {


        // Try block to check for exceptions
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            List<String> mailingList = emailDetails.getRecipient();
            for (String email : mailingList) {
                // Setting up necessary details
                mailMessage.setFrom(sender);
                mailMessage.setTo(email);
                mailMessage.setText(emailDetails.getMsBody());
                mailMessage.setSubject(emailDetails.getSubject());

                // Sending the mail
                mailSender.send(mailMessage);
            }

            return "Mail Sent Successfully...";
        }

// Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }

    }

    @Override
    public String sendSimpleMail(EmailDetails emailDetails) {
        return null;
    }

    @Override
    public String sendMailWithAttachmentToMany(EmailDetails emailDetails) {
        // Creating a mime message
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            List<String> mailingList = emailDetails.getRecipient();

            // Setting multipart as true for attachments to be sent
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setText(emailDetails.getMsBody());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            // Adding the attachment
            FileSystemResource file = new FileSystemResource(new File(emailDetails.getAttachment()));

            mimeMessageHelper.addAttachment(file.getFilename(), file);

            // Sending the mail
            for (String email : mailingList) {
                mimeMessageHelper.setTo(email);
                mailSender.send(mimeMessage);
            }

            return "Mail sent Successfully";
        }

        // Catch block to handle MessagingException
        catch (MessagingException e) {

            // Display message when exception occurred
            return "Error while sending mail!!!";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails emailDetails) {
        return null;
    }


}
