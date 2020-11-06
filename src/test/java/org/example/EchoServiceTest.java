package org.example;

import org.example.stubs.TestInputStream;
import org.example.stubs.TestOutputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.module.InvalidModuleDescriptorException;

import static org.junit.jupiter.api.Assertions.*;

class EchoServiceTest {
    ///TDD -> Test Driven Development
    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponded() throws IOException {
        ///Given
        EchoService echoService = new EchoService();
        String request = "Hello There";

        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream(request);

        ///When
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        ///Then
        String messageSent = outputStream.getMessageSent();

        assertEquals(request, messageSent);

        assertTrue(response);
    }

    @Test
    public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponded() throws IOException {
        ///Given
        EchoService echoService = new EchoService();
        String request = "Hello There";

        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream("Otra Cosa");

        ///When
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        ///Then
        String messageSent = outputStream.getMessageSent();

        assertEquals(request, messageSent);

        assertFalse(response);
    }

}