package edu.school21.chat.app;

import com.zaxxer.hikari.*;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.UsersRepository;
import edu.school21.chat.repositories.UsersRepositoryJdbcImpl;

import java.util.ArrayList;
import java.util.List;

public class Program {
    private static HikariDataSource ds = new HikariDataSource();

    public static void main(String[] args) {
        connect();
        List<User> tmp = new ArrayList<>();
        UsersRepository uRep = new UsersRepositoryJdbcImpl(ds);
        tmp = uRep.findAll(1,2);
        if (!tmp.isEmpty()){
            for (User o: tmp){
                System.out.println(o);
            }
        }
        disconnect();
    }

    public static void connect(){
//        dataSource.getConnection();
        ds.setJdbcUrl("jdbc:postgresql://localhost:5432/mmeredit");
    }

    public static void disconnect(){
        ds.close();
    }
}
