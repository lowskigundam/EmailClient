package com.example.emailclient.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "emails")
public class Email {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "sender")
    public String sender;

    @ColumnInfo(name = "subject")
    public String subject;

    @ColumnInfo(name = "body")
    public String body;

    @ColumnInfo(name = "date")
    public String date;
}
