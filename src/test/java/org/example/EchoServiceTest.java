package org.example;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServiceTest {
    ///TDD -> Test Driven Development
    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponded() throws IOException {
        ///Given
        EchoService echoService = new EchoService();
        String request = "Hello There";
        byte []messageInBytes = new byte[]{
          'H', 'e', 'l', 'l', 'o', ' ', 'T', 'h', 'e', 'r','e'
        };


        OutputStream outputStream = mock(OutputStream.class);
        InputStream inputStream = mock(InputStream.class);

        Mockito.when(inputStream.readAllBytes()).thenReturn(messageInBytes);

        ///When
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        ///Then
        verify(outputStream).write(messageInBytes);
        verify(inputStream).readAllBytes();
        verifyNoMoreInteractions(inputStream,outputStream);

        assertTrue(response);
    }

   @Test
   public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponded() throws IOException {
       ///Given
       EchoService echoService = new EchoService();
       String request = "Hello There";

       byte []messageResponse = new byte[]{   ///Wrong Request
               'H', 'e', 'l', 'l', 'o', ' ', 'T', 'h', 'e', 'r','e', 'e'
       };

       byte []messageRequest = new byte[]{
               'H', 'e', 'l', 'l', 'o', ' ', 'T', 'h', 'e', 'r','e'
       };

       OutputStream outputStream = mock(OutputStream.class);
       InputStream inputStream = mock(InputStream.class);

       when(inputStream.readAllBytes()).thenReturn(messageResponse);

       ///When
       boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

       ///Then
      verify(outputStream).write(messageRequest);
      verify(inputStream).readAllBytes();
      verifyNoMoreInteractions(inputStream,outputStream);

       assertFalse(response);
   }

}