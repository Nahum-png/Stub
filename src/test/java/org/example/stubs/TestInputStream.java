package org.example.stubs;

import java.io.IOException;
import java.io.InputStream;

public class TestInputStream extends InputStream {

    private final String messageToRespond;

    public TestInputStream(String messageToRespond){
        this.messageToRespond = messageToRespond;
    }

    @Override
    public int read() throws IOException {
        return 0;
    }

    @Override
    public byte[] readAllBytes() throws IOException {
        return messageToRespond.getBytes();
    }
}
