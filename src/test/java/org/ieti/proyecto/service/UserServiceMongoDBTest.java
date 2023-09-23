package org.ieti.proyecto.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.ieti.proyecto.repository.UserMongoRepository;
import org.ieti.proyecto.service.user.UserServiceMongoDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class UserServiceMongoDBTest {

    @InjectMocks
    private UserServiceMongoDB userService;

    @Mock
    private UserMongoRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void givenUserDTO_whenSaveUser_thenUserIsSaved() {
        // GIVEN
        UserDTO userDTO = new UserDTO("123", "John Doe", "john@example.com");
        User savedUser = new User(userDTO);
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // WHEN
        User result = userService.save(userDTO);

        // THEN
        assertEquals(savedUser.getEmail(), result.getEmail());
    }

    @Test
    public void givenUserId_whenFindUserById_thenUserIsFound() {
        // GIVEN
        String userId = "123";
        User user = new User(userId, "John Doe", "john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // WHEN
        Optional<User> result = userService.findById(userId);

        // THEN
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    public void givenUsers_whenFindAllUsers_thenAllUsersAreRetrieved() {
        // GIVEN
        List<User> users = new ArrayList<>();
        users.add(new User("1", "User1", "user1@example.com"));
        users.add(new User("2", "User2", "user2@example.com"));
        when(userRepository.findAll()).thenReturn(users);

        // WHEN
        List<User> result = userService.all();

        // THEN
        assertEquals(users.size(), result.size());
    }

    @Test
    public void givenUserIdAndUserDTO_whenUpdateUser_thenUserIsUpdated() {
        // GIVEN
        String userId = "123";
        UserDTO updatedUserDTO = new UserDTO("123", "Updated User", "updated@example.com");
        User existingUser = new User(userId, "John Doe", "john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(existingUser);

        // WHEN
        User result = userService.update(updatedUserDTO, userId);

        // THEN
        assertEquals(updatedUserDTO.getName(), result.getName());
        assertEquals(updatedUserDTO.getEmail(), result.getEmail());
    }

    @Test
    public void givenUserId_whenDeleteUserById_thenUserIsDeleted() {
        // GIVEN
        String userId = "123";

        // WHEN
        userService.deleteById(userId);

        // THEN
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void givenNonExistentUserId_whenUpdateUser_thenResponseStatusExceptionIsThrown() {
        // GIVEN
        String userId = "123";
        UserDTO updatedUserDTO = new UserDTO("123", "Updated User", "updated@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ResponseStatusException.class, () -> {
            userService.update(updatedUserDTO, userId);
        });
    }
}

