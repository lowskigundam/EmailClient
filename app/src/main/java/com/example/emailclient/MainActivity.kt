package com.example.emailclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import com.example.emailclient.viewmodel.EmailViewModel

class MainActivity : ComponentActivity() {
    private val vm: EmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Use setContent to build your UI with Compose
        setContent {
            // The root Composable for your screen
            EmailClientApp(vm)
        }

        // Example: fetch Gmail (replace with your credentials)
        // This should ideally be triggered by a user action or a specific lifecycle event
        vm.refreshEmails("imap.gmail.com", "your_email@gmail.com", "your_password")
    }
}

@Composable
fun EmailClientApp(viewModel: EmailViewModel) {
    // Observe the LiveData and display the email subjects
    val emails = viewModel.allEmails.observeAsState(initial = emptyList())

    // A simple example to display the first email's subject
    if (emails.value.isNotEmpty()) {
        Text(text = "Subject: ${emails.value[0].subject}")
    } else {
        Text(text = "Fetching emails...")
    }
}
