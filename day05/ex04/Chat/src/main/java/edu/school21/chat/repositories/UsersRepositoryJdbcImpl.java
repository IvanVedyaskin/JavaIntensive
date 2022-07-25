package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    HikariDataSource ds;

    public UsersRepositoryJdbcImpl(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> lst = new ArrayList<>();
        if (page <= 0 || size <= 0){
            System.err.println("Argument non-valid");
            System.exit(1);
        }
        try {
            Connection conn = ds.getConnection();
            String state = "Select * from \"user\" offset ? limit ?;";
            PreparedStatement pState = conn.prepareStatement(state);
            pState.setLong(1, page * size - 1);
            pState.setLong(2, size);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return new ArrayList<>();
            }
            while (true){
                User user = new User();
                user.setId(res.getLong(1));
                user.setLogin(res.getString(2));
                user.setPassword(res.getString(3));
                lst.add(user);
                if (!res.next()){
                    break;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error");
            System.exit(1);
        }
        return lst;
    }
}
