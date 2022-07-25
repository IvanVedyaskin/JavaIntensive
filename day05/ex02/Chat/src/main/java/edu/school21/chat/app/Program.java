package edu.school21.chat.app;

import com.zaxxer.hikari.*;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Program {
    private static HikariDataSource dataSource = new HikariDataSource();

    public static void main(String[] args) {
        connect();

        User creator = new User(7L, "user", "user", new ArrayList(), new ArrayList());
        User author = creator;
        Chatroom chatroom = new Chatroom(8L, "room", creator, new ArrayList<>());
        Message message = new Message(6L, author, chatroom, "Hello!", LocalDateTime.now());
        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        messagesRepository.save(message);
        Optional<Message> checkFindBy = messagesRepository.findById(6L);
        if (checkFindBy.isPresent()) {
            System.out.println(checkFindBy.get().toString());
        }
        disconnect();
    }

    public static void connect(){
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/mmeredit");
    }

    public static void disconnect(){
        dataSource.close();
    }
}
