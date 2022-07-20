package ex04;

public class UsersArrayList implements UsersList {
    private static final int CAPACITY = 10;
    private User[] usersArrayLists;
    private int size = 0;

    static class UserNotFoundException extends RuntimeException{
        public UserNotFoundException() {}
    }

    @Override
    public void add(User user) {
        if (usersArrayLists.length - 1 >= size) {
            usersArrayLists = newArrayList(usersArrayLists);
        }
        usersArrayLists[size] = user;
        setSize();
    }

    @Override
    public User getUserByIndex(int index){
        if (index <= usersArrayLists.length - 1){
            return usersArrayLists[index];
        }
        throw new UserNotFoundException();
    }

    @Override
    public User getUserById(int id) {
        for (int i = 0; i < size; i++){
            if (usersArrayLists[i].getId() == id){
                return usersArrayLists[i];
            }
        }
       return null;
    }

    private User[] newArrayList(User[] u){
        User[] tmp = new User[usersArrayLists.length * 2];

        for (int i = 0; i < u.length; i++){
            tmp[i] = u[i];
        }
        return tmp;
    }

    private void setSize(){
        int i = 0;

        while (usersArrayLists[i] != null && i < usersArrayLists.length){
            i++;
        }
        size = i;
    }

    @Override
    public int getSize() {
        return size;
    }

    public UsersArrayList(User[] userArray) {
        if ((userArray != null) && (size = userArray.length) != 0) {
            usersArrayLists = userArray;
            setSize();
        }
        else {
            usersArrayLists = new User[]{};
        }
    }

    public UsersArrayList(){
        usersArrayLists = new User[CAPACITY];
    }

    public UsersArrayList(int initialCapacity){
        if (initialCapacity > 0) {
            usersArrayLists = new User[initialCapacity];
            setSize();
        }
        else if (initialCapacity == 0){
            usersArrayLists = new User[]{};
        }
        else {
            throw new IllegalArgumentException("Illegal Capacity: "+
                    initialCapacity);
        }
    }
}
