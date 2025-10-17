package com.example.emailclient;

import org.junit.Test;
import static org.junit.Assert.*;

public class BackendTest {

    @Test
    public void testBackend() {
        EmailBackend backend = new EmailBackend();

        // Example: test your backend logic
        boolean connected = backend.connectToServer(
                "imap.gmail.com", 993, "you@example.com", "password"
        );

        assertTrue("Should connect to server", connected);
    }
}
