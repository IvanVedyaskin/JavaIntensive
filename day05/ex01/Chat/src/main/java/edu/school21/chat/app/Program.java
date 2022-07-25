package edu.school21.chat.app;

import java.util.Optional;
import java.util.Scanner;
import com.zaxxer.hikari.*;
import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

public class Program {
    private static HikariDataSource dataSource = new HikariDataSource();

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter a message ID");
        if (scan.hasNextLong()){
            long id = scan.nextLong();
            connect();
            MessagesRepositoryJdbcImpl mesRep = new MessagesRepositoryJdbcImpl(dataSource);
            Optional<Message> checkFindBy = mesRep.findById(id);
            if (checkFindBy.isPresent()) {
                System.out.println(checkFindBy.get().toString());
            }
            else {
                System.err.println("Id not found");
            }
            disconnect();
        }
        else{
            System.err.println("Argument is non-valid");
            System.exit(1);
        }
    }

    public static void connect(){
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/mmeredit");
    }

    public static void disconnect(){
        dataSource.close();
    }
}
