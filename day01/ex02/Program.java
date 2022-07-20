package ex02;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Alexandr", 500);
        User user2 = new User("Alexey", 100);

        UsersArrayList users = new UsersArrayList();
        users.add(user1);
        users.add(user2);
        System.out.println(users.getUserByIndex(0).getName());
        System.out.println(users.getUserByIndex(1).getName());
    }
}
