package com.example.onlychat.model.data;

import com.example.onlychat.model.Message;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Configuration
public class MessageDB {

    private final List<Message> messages = new ArrayList<>();

    public boolean postMessage(Message message) {
        return messages.add(message);
    }

    public List<Message> getMessages() {
        List<Message> msgs = new ArrayList<>(messages);
        Collections.reverse(msgs);
        return msgs;
    }
}
