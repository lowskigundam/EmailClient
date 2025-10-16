package com.example.emailclient

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import com.example.emailclient.viewmodel.EmailViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var vm: EmailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        vm = ViewModelProvider(this)[EmailViewModel::class.java]

        // Example: fetch Gmail (replace with your credentials)
        vm.refreshEmails("imap.gmail.com", "your_email@gmail.com", "your_password")

        vm.allEmails.observe(this) { emails ->
            for (e in emails) {
                println("Subject: ${e.subject}")
            }
        }
    }
}
