package com.weebindustry.weebjournal.controllers;

import java.util.List;
import java.util.Optional;
import java.net.*;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.users.*;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserRepository repo;

    @GetMapping("/")
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(repo.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) {

        Optional<User> result = repo.findById(id);

        return result.map(response -> ResponseEntity.ok().body(response)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
        log.info("Request to create user: {}", user);
        User result = repo.save(user);
        return ResponseEntity.created(new URI("/users/" + result.getId())).body(result);
    }


    @PostMapping("/login")
    public ResponseEntity<String> loginValidation(@RequestParam String username, @RequestParam String password) {
        Optional<User> result = repo.loginValidation(username, password);

        if (!result.isPresent()) {
            log.error("Logged in failed from user with username = {}", username);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Logged in as " + username);
    }

    @PostMapping("/register")
    public ResponseEntity<User> userRegistration(@Valid @RequestBody UserRegistrationDTO dto) {
        User user = new User();

        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setDisplayname(dto.getDisplayname());
        user.setBiography(dto.getBiography());
        user.setDateOfBirth(dto.getDateOfBirth());

        return ResponseEntity.ok(repo.save(user));
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) {
        Optional<User> result = repo.findById(id);

        if (!result.isPresent()) {
            log.error("User not found with id {}", id);
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(repo.save(result.get()));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       if (!repo.findById(id).isPresent()) {
           log.error("ID {} is not existed!", "id");
           ResponseEntity.badRequest().build();
       }

        repo.deleteById(id);

        return ResponseEntity.ok().build();
    }
}