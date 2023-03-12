package com.example.SpringEmailUsingApis.mailing.Models;


import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmailDetails {
    private List<String> recipient;
    private String msBody;
    private String subject;
    private String attachment;

    public List<String> getRecipient() {
        return recipient;
    }

    public void setRecipient(List<String> recipient) {
        this.recipient = recipient;
    }

    public String getMsBody() {
        return msBody;
    }

    public void setMsBody(String msBody) {
        this.msBody = msBody;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
