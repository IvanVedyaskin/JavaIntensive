package edu.school21.sockets.services;

import org.springframework.stereotype.Component;

@Component
public interface UserService {
    boolean signUp(String name, String password);
}
