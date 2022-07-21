package ex01;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Alphabet {
    private HashMap<String, Integer> myVector;

    public Alphabet() {
        this.myVector = new HashMap<>();
    }

    public void setMyVector(String key) {
        myVector.put(key, myVector.getOrDefault(key, 0) + 1);
    }

    public int getValue(String key){
        return myVector.getOrDefault(key, 0).intValue();
    }
}
