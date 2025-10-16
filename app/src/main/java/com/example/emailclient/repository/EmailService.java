package com.example.emailclient.repository;

import com.example.emailclient.model.Email;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailService {

    // Fetch emails
    public List<Email> fetchEmails(String host, String user, String pass) throws Exception {
        List<Email> list = new ArrayList<>();

        Properties props = new Properties();
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect(host, user, pass);

        Folder inbox = store.getFolder("INBOX");
        inbox.open(Folder.READ_ONLY);
        Message[] messages = inbox.getMessages();

        for (Message msg : messages) {
            Email email = new Email();
            email.sender = msg.getFrom()[0].toString();
            email.subject = msg.getSubject();
            email.body = msg.getContent().toString();
            email.date = msg.getReceivedDate().toString();
            list.add(email);
        }

        inbox.close(false);
        store.close();
        return list;
    }

    // Send emails
    public void sendEmail(String host, String user, String pass,
                          String to, String subject, String body) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(user));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));
        message.setSubject(subject);
        message.setText(body);

        Transport.send(message);
    }
}
