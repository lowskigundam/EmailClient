package com.example.emailclient.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.emailclient.model.Email;
import java.util.List;

@Dao
public interface EmailDao {
    @Insert
    void insert(Email email);

    @Query("SELECT * FROM emails ORDER BY id DESC")
    LiveData<List<Email>> getAllEmails();

    @Query("DELETE FROM emails")
    void deleteAll();
}
