package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {
    private long id;
    private String name;
    private User ownerUser;
    private List<Message> allMessages;

    public Chatroom(long id, String name, User ownerUser, List<Message> allMessages) {
        this.id = id;
        this.name = name;
        this.ownerUser = ownerUser;
        this.allMessages = allMessages;
    }

    public Chatroom(){

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwnerUser() {
        return ownerUser;
    }

    public List<Message> getAllMessages() {
        return allMessages;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwnerUser(User ownerUser) {
        this.ownerUser = ownerUser;
    }

    public void setAllMessages(List<Message> allMessages) {
        this.allMessages = allMessages;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + id +
                ", chatroomName='" + name + '\'' +
                ", ownerUser=" + ownerUser +
                ", allMessages=" + allMessages +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, ownerUser, allMessages);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Chatroom guest = (Chatroom) obj;
        return id == guest.id && name.equals(guest.name) && ownerUser.equals(guest.ownerUser)
                && allMessages.equals(allMessages);
    }
}
