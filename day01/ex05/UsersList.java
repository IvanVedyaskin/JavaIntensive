package ex05;

public interface UsersList {
    void add(User user);
    User getUserByIndex(int index);
    User getUserById(int id);
    int getSize();
}
