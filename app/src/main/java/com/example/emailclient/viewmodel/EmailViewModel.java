package com.example.emailclient.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.*;
import com.example.emailclient.model.Email;
import com.example.emailclient.repository.EmailRepository;
import java.util.List;

public class EmailViewModel extends AndroidViewModel {
    private EmailRepository repo;
    private LiveData<List<Email>> allEmails;

    public EmailViewModel(@NonNull Application app) {
        super(app);
        repo = new EmailRepository(app);
        allEmails = repo.getAllEmails();
    }

    public LiveData<List<Email>> getAllEmails() {
        return allEmails;
    }

    public void refreshEmails(String host, String user, String pass) {
        repo.fetchAndSaveEmails(host, user, pass);
    }

    public void sendEmail(String host, String user, String pass,
                          String to, String subject, String body) {
        repo.sendEmail(host, user, pass, to, subject, body);
    }
}
