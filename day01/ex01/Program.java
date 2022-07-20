package ex01;

public class Program {
    public static void main(String[] args) {
        User user1 = new User("Ivan", 100);
        User user2 = new User("Andrey", 150);
        System.out.println("User1 id = " + user1.getId());
        System.out.println("User2 id = " + user2.getId());
    }
}
