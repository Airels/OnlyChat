package com.example.onlychat.view;

import com.example.onlychat.model.Message;
import com.example.onlychat.model.data.MessageDB;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;

@Component
public class MessageHandlerView {

    public static List<Message> getMessages(MessageDB repository) {
        return repository.getMessages();

        /*
        StringBuilder jsonRes = new StringBuilder();
        List<Message> messages = repository.getMessages();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");

        jsonRes.append("[");

        boolean first = true;
        for (Message message: messages) {
            if (!first) jsonRes.append(", ");
            else first = false;

            jsonRes.append("\"(")
                    .append(formatter.format(message.getTimestamp()))
                    .append(") ")
                    .append(message.getAuthor().getUsername())
                    .append(": ")
                    .append(message.getContent())
                    .append("\"");
        }

        jsonRes.append("]");
        return jsonRes.toString();
         */
    }
}
