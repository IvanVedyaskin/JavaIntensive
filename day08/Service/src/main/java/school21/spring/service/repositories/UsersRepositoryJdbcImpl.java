package school21.spring.service.repositories;

import com.zaxxer.hikari.HikariDataSource;
import school21.spring.service.models.User;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class UsersRepositoryJdbcImpl implements UsersRepository{
    HikariDataSource ds;

    public UsersRepositoryJdbcImpl(SetDataSource ds) {
        this.ds = ds.ds;
    }

    static class SetDataSource{
        HikariDataSource ds;
        Properties properties;

        public SetDataSource() {
            try {
                properties = new Properties();
                InputStream is = SetDataSource.class.getClassLoader().getResourceAsStream("db.properties");
                properties.load(is);
                if (is != null) {
                    is.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
            ds = new HikariDataSource();
            ds.setJdbcUrl(properties.getProperty("db.url"));
            ds.setUsername(properties.getProperty("db.user"));
            ds.setPassword(properties.getProperty("db.password"));
        }
    }

    @Override
    public User findById(Long id) {
        try{
            Connection conn = ds.getConnection();
            String state = "select * from users where id = ?;";
            PreparedStatement pState = conn.prepareStatement(state);
            pState.setLong(1, id);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return null;
            }
            User user = new User();
            user.setIdentifier(res.getLong(1));
            user.setEmail(res.getString(2));
            return user;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try {
            Connection conn = ds.getConnection();
            String state = "select * from users;";
            PreparedStatement pState = conn.prepareStatement(state);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return users;
            }
            while (true){
                User user = new User();
                user.setIdentifier(res.getLong(1));
                user.setEmail(res.getString(2));
                users.add(user);
                if (!res.next()){
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public void save(User entity) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Insert Into users values (?, ?);";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, entity.getIdentifier());
            pState.setString(2, entity.getEmail());
            if (pState.executeUpdate() == 0){
                System.err.println("Arguments are non-valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User entity) {
        try {
            Connection conn = ds.getConnection();
            String statement;
            ResultSet res;
            statement = "Select * from users where id = ?";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, entity.getIdentifier());
            res = pState.executeQuery();
            if (!res.next()) {
                return;
            }
            if ((res.getLong(1) != entity.getIdentifier())
                    || (!res.getString(2).equals(entity.getEmail()))) {
                statement = "UPDATE users SET email = ? where id = ?;";
                pState = conn.prepareStatement(statement);
                pState.setString(1, entity.getEmail());
                pState.setLong(2, entity.getIdentifier());
                pState.executeUpdate();
                if (pState.executeUpdate() == 0){
                    return;
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Delete from users where id = ? ;";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, id);
            if (pState.executeUpdate() == 0){
                System.err.println("Arguments are non-valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try{
            Connection conn = ds.getConnection();
            String state = "select * from users where email = ?;";
            PreparedStatement pState = conn.prepareStatement(state);
            pState.setString(1, email);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                return Optional.empty();
            }
            User user = new User();
            user.setIdentifier(res.getLong(1));
            user.setEmail(res.getString(2));
            return Optional.of(user);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
