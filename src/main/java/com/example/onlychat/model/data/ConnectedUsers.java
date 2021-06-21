package com.example.onlychat.model.data;

import com.example.onlychat.model.User;
import kotlin.Pair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Scope("prototype")
public class ConnectedUsers {

    private static final List<Pair<Integer, User>> connectedUsers = new ArrayList<>();

    public int addUser(User user) {
        int hashCode = user.hashCode();
        connectedUsers.add(new Pair<>(hashCode, user));
        return hashCode;
    }

    public void removeUser(int hash) {
        for (int i = 0; i < connectedUsers.size(); i++) {
            Pair<Integer, User> connectedUser = connectedUsers.get(i);
            if (connectedUser.component1() == hash) {
                connectedUsers.remove(i);
                return;
            }
        }
    }

    public boolean isConnected(int hash) {
        for (Pair<Integer, User> connectedUser : connectedUsers) {
            if (connectedUser.component1() == hash)
                return true;
        }

        return false;
    }

    public User getUser(int hash) {
        for (Pair<Integer, User> connectedUser : connectedUsers) {
            if (connectedUser.component1() == hash)
                return connectedUser.component2();
        }

        return null;
    }
}
