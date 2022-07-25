package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository{
    private HikariDataSource ds;

    private static class NotSavedSubEntityException extends RuntimeException{
        public NotSavedSubEntityException(String message) {
            super(message);
        }

        public NotSavedSubEntityException() {
        }
    }

    public MessagesRepositoryJdbcImpl(HikariDataSource ds) {
        this.ds = ds;
    }

    @Override
    public void update(Message msg) {
        try {
            Connection conn = ds.getConnection();
            String statement;
            ResultSet res;
            statement = "Select * from Message where id = ?";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, msg.getId());
            res = pState.executeQuery();
            if (!res.next()){
                return;
            }
            if ((res.getLong(1) != msg.getId())
                    || (res.getLong(2) != msg.getAuthor().getId())
                    || (res.getLong(3) != msg.getRoom().getId())
                    || (!res.getString(4).equals(msg.getText()))
                    || (!res.getTimestamp(5).equals(msg.getTimestamp()))){
                statement = "UPDATE message SET id = ?, userid = ?, roomid = ?, texts = ?, date = ? where id = ? RETURNING *;";
                pState = conn.prepareStatement(statement);
                pState.setLong(1, msg.getId());
                pState.setLong(2, msg.getAuthor().getId());
                pState.setLong(3, msg.getRoom().getId());
                pState.setString(4, msg.getText());
                pState.setTimestamp(5, msg.getTimestamp());
                pState.setLong(6, msg.getId());
                res = pState.executeQuery();
                if (!res.next()){
                    return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save(Message msg) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Insert Into message values (?, ?, ?, ?, ?) RETURNING *;";
            PreparedStatement pState = conn.prepareStatement(statement);
            pState.setLong(1, msg.getId());
            pState.setLong(2, msg.getAuthor().getId());
            pState.setLong(3, msg.getRoom().getId());
            pState.setString(4, msg.getText());
            pState.setTimestamp(5, msg.getTimestamp());
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                System.err.println("Arguments are non-valid");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            Connection conn = ds.getConnection();
            String statement = "Select login, password, u.id as u_id, c.id as c_id, chatroomname, texts, date from Message\n" +
                    "inner join \"user\" u on u.id = userid\n" +
                    "inner join chatroom c on c.id = message.roomid\n" +
                    "where message.id = " + id + " ;";
            PreparedStatement pState = conn.prepareStatement(statement);
            ResultSet res = pState.executeQuery();
            if (!res.next()){
                throw new NotSavedSubEntityException();
            }
            User user = new User();
            Chatroom room = new Chatroom();
            Message msg = new Message(id, user, room, null, null);
            user.setLogin(res.getString("login"));
            user.setPassword(res.getString("password"));
            room.setId(res.getLong("c_id"));
            user.setId(res.getLong("u_id"));;
            room.setName(res.getString("chatroomname"));
            msg.setText(res.getString("texts"));
            msg.setTimestamp(res.getTimestamp("date").toLocalDateTime());
            return Optional.of(msg);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
