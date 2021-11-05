package com.example.onlychat.model.data;

import com.example.onlychat.model.Message;
import com.example.onlychat.model.User;
import com.example.onlychat.model.data.entities.MessagesEntity;
import com.example.onlychat.model.data.entities.UsersEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");

    public static User getUser(String username) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<UsersEntity> query = entityManager.createNamedQuery("UsersEntity.get", UsersEntity.class);
            query.setParameter(1, username);

            return new User(query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static User loginUser(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            TypedQuery<UsersEntity> query = entityManager.createNamedQuery("UsersEntity.login", UsersEntity.class);
            query.setParameter(1, username);
            query.setParameter(2, password);

            return new User(query.getSingleResult());
        } catch (NoResultException e) {
            return null;
        } finally {
            entityManager.close();
        }
    }

    public static boolean registerUser(String username, String password) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            UsersEntity entity = new UsersEntity();
            entity.setUsername(username);
            entity.setPassword(password);
            entityManager.persist(entity);

            transaction.commit();
            return true;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
        }
    }

    public static List<Message> getMessages() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Message> messages = new ArrayList<>();

        try {
            TypedQuery<MessagesEntity> query = entityManager.createNamedQuery("MessagesEntity.get", MessagesEntity.class);

            for (MessagesEntity e : query.getResultList())
                messages.add(new Message(e));
        } catch (NoResultException ignored) {}

        entityManager.close();

        return messages;
    }

    public static boolean pushMessage(Message message) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            MessagesEntity entity = new MessagesEntity();
            entity.setAuthor(message.getAuthor());
            entity.setContent(message.getContent());
            entity.setTimestamp(new Timestamp(message.getTimestamp()));
            entityManager.persist(entity);

            transaction.commit();
            return true;
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }

            entityManager.close();
        }
    }
}
