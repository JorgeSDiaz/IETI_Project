package org.ieti.proyecto.controller.user;

import org.ieti.proyecto.exception.UserNotFoundException;
import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.ieti.proyecto.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1.0/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody UserDTO newUser) {
        User user = userService.save(newUser);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.all(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") String userId) {
        Optional<User> userSearched = userService.findById(userId);
        if (userSearched.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return new ResponseEntity<>(userSearched.get(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@RequestBody UserDTO userUpdates, @PathVariable("id") String userId) {
        if (userService.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return new ResponseEntity<>(userService.update(userUpdates, userId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") String userId) {
        if (userService.findById(userId).isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        userService.deleteById(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
