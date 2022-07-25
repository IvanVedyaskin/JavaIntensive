package edu.school21.chat.app;

import com.zaxxer.hikari.*;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Optional;

public class Program {
    private static HikariDataSource dataSource = new HikariDataSource();

    public static void main(String[] args) {
        connect();

        MessagesRepository messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(3L);
        if (messageOptional.isPresent()){
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setTimestamp(null);
            messagesRepository.update(message);
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
