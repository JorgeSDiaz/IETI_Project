package org.ieti.proyecto.controller.user;

import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.ieti.proyecto.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/v1/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDTO newUser) {
        return new ResponseEntity<>(userService.save(new User(newUser)), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.findById(userId).get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userUpdates, @PathVariable("id") String userId) {
        return new ResponseEntity<>(userService.update(userUpdates, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
