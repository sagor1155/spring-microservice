package com.learn.restfulwebservices.user;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private final UserDaoService userDaoService;

    public UserResource(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUser() {
        return userDaoService.findAll();
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable Integer id) {
        User user = userDaoService.findById(id);

        if (user == null) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        return user;
    }

    @PostMapping(value = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userDaoService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userDaoService.deleteById(id);

    }


}
