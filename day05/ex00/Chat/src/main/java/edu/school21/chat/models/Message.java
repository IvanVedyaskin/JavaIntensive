package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {
    private int id;
    private User author;
    private Chatroom room;
    private String text;
    private Date timestamp;

    public Message(int messageId, User messageAuthor, Chatroom room, String text, Date timestamp) {
        this.id = messageId;
        this.author = messageAuthor;
        this.room = room;
        this.text = text;
        this.timestamp = timestamp;
    }

    public int getId() {
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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
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
        return "Message{" +
                "id=" + id +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}