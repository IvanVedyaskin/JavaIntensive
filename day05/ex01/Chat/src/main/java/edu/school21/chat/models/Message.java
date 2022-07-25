package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {
    private long id;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime timestamp;

    public Message(long messageId, User messageAuthor, Chatroom room, String text, LocalDateTime timestamp) {
        this.id = messageId;
        this.author = messageAuthor;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, author, room, text, timestamp);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Message guest = (Message) obj;
        return id == guest.id && author.equals(guest.author) && room.equals(guest.room)
                && text.equals(guest.text) && timestamp.equals(guest.timestamp);
    }

    @Override
    public String toString() {
        return "Message : \n" +
                "id=" + id + ",\n" +
                "author=" + author + ",\n" +
                "room=" + room + ",\n" +
                "text=" + "\"" + text + "\"" + '\n' +
                "timestamp=" + timestamp +
                "\n}";
    }
}
