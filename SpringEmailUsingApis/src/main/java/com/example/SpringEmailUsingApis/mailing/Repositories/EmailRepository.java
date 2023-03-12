package com.example.SpringEmailUsingApis.mailing.Repositories;


import com.example.SpringEmailUsingApis.mailing.Models.EmailDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository {


    String sendSimpleMailToMany(EmailDetails emailDetails);
    String sendSimpleMail(EmailDetails emailDetails);
    String sendMailWithAttachmentToMany(EmailDetails emailDetails);
    String sendMailWithAttachment(EmailDetails emailDetails);


}
