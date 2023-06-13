package com.learn.restfulwebservices.user;

import com.learn.restfulwebservices.post.Post;
import com.learn.restfulwebservices.post.PostRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJpaResource {

    private final UserJpaRepository userRepository;
    private final PostRepository postRepository;

    public UserJpaResource(UserJpaRepository userJpaRepository, PostRepository postRepository) {
        this.userRepository = userJpaRepository;
        this.postRepository = postRepository;
    }

    @GetMapping(value = "/api/users")
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/api/users/{id}")
    public User getUser(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        return user.get();
    }

    @PostMapping(value = "/api/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User savedUser = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/api/users/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userRepository.deleteById(id);
    }

    @GetMapping(value = "/api/users/{id}/posts")
    public List<Post> getPosts(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        return user.get().getPosts();
    }

    @PostMapping(value = "/api/users/{id}/posts")
    public ResponseEntity<Object> createPostForUser(@PathVariable Integer id, @Valid @RequestBody Post post) {
        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFountException("User not found with Id=" + id);
        }

        post.setUser(user.get());

        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
