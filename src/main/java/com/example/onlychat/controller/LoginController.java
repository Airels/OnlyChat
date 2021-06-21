package com.example.onlychat.controller;

import com.example.onlychat.exception.UserAlreadyExistException;
import com.example.onlychat.exception.WrongUserCredentialsException;
import com.example.onlychat.model.data.ConnectedUsers;
import com.example.onlychat.model.User;
import com.example.onlychat.model.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private UserRepository repository;
    @Autowired
    private ConnectedUsers connectedUsers;

    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public int connectUser(@RequestParam String username, @RequestParam String password) {
        User user = repository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password))
            throw new WrongUserCredentialsException();

        return connectedUsers.addUser(user);
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseBody
    public int registerUser(@RequestParam String username, @RequestParam String password) {
        if (repository.findByUsername(username) != null)
            throw new UserAlreadyExistException();

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        repository.insert(user);
        return connectedUsers.addUser(user);
    }

    @RequestMapping("/connected")
    @ResponseBody
    public boolean isUserConnected(@RequestParam String hash) {
        try {
            return connectedUsers.isConnected(Integer.parseInt(hash));
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/disconnect")
    @ResponseStatus(HttpStatus.OK)
    public void userDisconnect(@RequestParam int hash) {
        connectedUsers.removeUser(hash);
    }
}
