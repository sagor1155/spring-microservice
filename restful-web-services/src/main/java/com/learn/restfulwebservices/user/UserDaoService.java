package com.learn.restfulwebservices.user;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class UserDaoService {

    private static final List<User> users = new ArrayList<>();
    private static Integer countUser = 0;

    static {
        users.add(new User(++countUser, "Adam", LocalDate.now().minusYears(31)));
        users.add(new User(++countUser, "Erica", LocalDate.now().minusYears(30)));
        users.add(new User(++countUser, "Melissa", LocalDate.now().minusYears(29)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findById(Integer id) {
        Predicate<User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
    }

    public User save(User user) {
        user.setId(++countUser);
        users.add(user);
        return user;
    }

    public void deleteById(Integer id) {
        Predicate<User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }

}
