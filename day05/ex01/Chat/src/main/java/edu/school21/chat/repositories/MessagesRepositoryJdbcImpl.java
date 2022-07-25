package edu.school21.chat.repositories;

import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private HikariDataSource ds;

    public MessagesRepositoryJdbcImpl(HikariDataSource ds) {
        this.ds = ds;
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
                return Optional.empty();
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
