package edu.school21.sockets.services;

import edu.school21.sockets.models.User;
import edu.school21.sockets.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component("userService")
public class UserServiceImpl implements UserService {

    UsersRepository usersRepository;
    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(@Qualifier("jdbcTemplate") UsersRepository usersRepository,
                           @Qualifier("getPasswordEncoder") PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean signUp(String name, String password) {
        password = passwordEncoder.encode(password);
        usersRepository.save(new User(1, name, password));
        return true;
    }
}
