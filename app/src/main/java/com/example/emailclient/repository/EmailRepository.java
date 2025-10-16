package com.example.emailclient.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.emailclient.data.AppDatabase;
import com.example.emailclient.data.EmailDao;
import com.example.emailclient.model.Email;
import java.util.*;
import java.util.concurrent.*;

public class EmailRepository {
    private EmailDao emailDao;
    private LiveData<List<Email>> allEmails;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public EmailRepository(Application app) {
        AppDatabase db = AppDatabase.getDatabase(app);
        emailDao = db.emailDao();
        allEmails = emailDao.getAllEmails();
    }

    public LiveData<List<Email>> getAllEmails() {
        return allEmails;
    }

    public void fetchAndSaveEmails(String host, String user, String pass) {
        executor.execute(() -> {
            try {
                EmailService service = new EmailService();
                List<Email> emails = service.fetchEmails(host, user, pass);
                for (Email e : emails) {
                    emailDao.insert(e);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void sendEmail(String host, String user, String pass,
                          String to, String subject, String body) {
        executor.execute(() -> {
            try {
                new EmailService().sendEmail(host, user, pass, to, subject, body);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
