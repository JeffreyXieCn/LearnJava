package com.in28minutes.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
  @Autowired private UserRepository userRepository;
  @Autowired private PostRepository postRepository;

  // retrieveAllUsers
  @GetMapping("/jpa/users")
  public List<User> retrieveAllUsers() {
    return userRepository.findAll();
  }

  @GetMapping("/jpa/users/{userId}")
  public Resource<User> retrieveUser(@PathVariable int userId) {
    Optional<User> user = userRepository.findById(userId);
    if (!user.isPresent()) {
      throw new UserNotFoundException("id-" + userId);
    }

    // HATEOAS
    // "all-users", SERVER_PATH + "/users"
    // retrieveAllUsers
    Resource<User> resource = new Resource<>(user.get());
    ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());

    resource.add(linkTo.withRel("all-users"));

    return resource;
  }

  @DeleteMapping("/jpa/users/{userId}")
  public void deleteUser(@PathVariable int userId) {
    userRepository.deleteById(userId);
  }

  @PostMapping("/jpa/users")
  public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
    User savedUser = userRepository.save(user);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(savedUser.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }

  @GetMapping("/jpa/users/{userId}/posts")
  public List<Post> retrieveAllPostsOfUser(@PathVariable int userId) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id-" + userId);
    }

    return userOptional.get().getPosts();
  }

  @PostMapping("/jpa/users/{userId}/posts")
  public ResponseEntity<Object> createPost(
      @PathVariable int userId, @Valid @RequestBody Post post) {
    Optional<User> userOptional = userRepository.findById(userId);
    if (!userOptional.isPresent()) {
      throw new UserNotFoundException("id-" + userId);
    }

    post.setUser(userOptional.get());

    // Post savedPost = postRepository.save(post);
    postRepository.save(post);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(post.getId())
            .toUri();
    return ResponseEntity.created(location).build();
  }
}
