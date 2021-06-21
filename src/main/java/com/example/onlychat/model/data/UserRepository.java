package com.example.onlychat.model.data;

import com.example.onlychat.model.User;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}
