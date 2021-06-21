package com.example.onlychat.controller;

import com.example.onlychat.exception.UserNotConnectedException;
import com.example.onlychat.model.*;
import com.example.onlychat.model.data.ConnectedUsers;
import com.example.onlychat.model.data.MessageDB;
import com.example.onlychat.model.handlers.SocketHandler;
import com.example.onlychat.view.MessageHandlerView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/messages")
public class MessageHandlerController {

    @Autowired
    private MessageDB messageRepository;
    @Autowired
    private ConnectedUsers connectedUsers;
    @Autowired
    private SocketHandler socketHandler;

    @GetMapping()
    @ResponseBody
    public List<Message> getMessages() {
        return MessageHandlerView.getMessages(messageRepository);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.OK)
    public void sendMessage(@RequestParam int userHash, @RequestParam String message) {
        User user = connectedUsers.getUser(userHash);
        if (user == null)
            throw new UserNotConnectedException();

        messageRepository.postMessage(new Message(user, message, System.currentTimeMillis()));
        socketHandler.pingAllClients();
    }
}
