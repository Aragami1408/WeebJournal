package com.weebindustry.weebjournal.controllers;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.Valid;

import com.weebindustry.weebjournal.dtos.users.*;
import com.weebindustry.weebjournal.models.User;
import com.weebindustry.weebjournal.repositories.UserRepository;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private static final ModelMapper modelMapper = new ModelMapper();

    
    private UserRepository repo;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserRepository repo, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.repo = repo;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        repo.save(user);
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


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
       if (!repo.findById(id).isPresent()) {
           log.error("ID {} is not existed!", "id");
           ResponseEntity.badRequest().build();
       }

        repo.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoleId(2);
        repo.save(user);
    }
}