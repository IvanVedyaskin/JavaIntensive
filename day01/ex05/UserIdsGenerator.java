package ex05;

public class UserIdsGenerator {
    private static final UserIdsGenerator GENERATOR = new UserIdsGenerator();
    private static int uniqId;

    public int generateId(){
        return ++uniqId;
    }

    public static UserIdsGenerator getInstance(){
        return GENERATOR;
    }

    private UserIdsGenerator() {};
}
