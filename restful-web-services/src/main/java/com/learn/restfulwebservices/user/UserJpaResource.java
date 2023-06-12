package com.learn.restfulwebservices.user;

import com.learn.restfulwebservices.post.Post;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private final UserJpaRepository userJpaRepository;

    public UserJpaResource(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @GetMapping(value = "/api/users")
    public List<User> getAllUser() {
        return userJpaRepository.findAll();
    }

    @GetMapping(value = "/api/users/{id}")
    public User getUser(@PathVariable Integer id) {
        Optional<User> user = userJpaRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        return user.get();
    }

    @PostMapping(value = "/api/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userJpaRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/api/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userJpaRepository.deleteById(id);
    }

    @GetMapping(value = "/api/users/{id}/posts")
    public List<Post> getPosts(@PathVariable Integer id) {
        Optional<User> user = userJpaRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        return user.get().getPosts();
    }
}
