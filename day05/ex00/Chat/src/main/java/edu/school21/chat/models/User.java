package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> userDefine;

    public User(int id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> userDefine) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.userDefine = userDefine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedRooms() {
        return createdRooms;
    }

    public void setCreatedRooms(List<Chatroom> createdRooms) {
        this.createdRooms = createdRooms;
    }

    public List<Chatroom> getUserDefine() {
        return userDefine;
    }

    public void setUserDefine(List<Chatroom> userDefine) {
        this.userDefine = userDefine;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, createdRooms, userDefine);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User guest = (User) obj;
        return id == guest.id && login.equals(guest.login) && password.equals(guest.password)
                && createdRooms.equals(guest.createdRooms) && userDefine.equals(guest.userDefine);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdRooms=" + createdRooms +
                ", userDefine=" + userDefine +
                '}';
    }
}