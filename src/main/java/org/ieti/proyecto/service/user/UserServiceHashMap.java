package org.ieti.proyecto.service.user;

import org.ieti.proyecto.exception.UserNotFoundException;
import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

public class UserServiceHashMap implements UserService {
    private final Map<String, User> userMap;

    private UserServiceHashMap() {
        this.userMap = new HashMap<>();
    }

    @Override
    public User save(UserDTO newUser) {
        this.userMap.put(String.valueOf(this.userMap.size() + 1),
                new User(String.valueOf(this.userMap.size() + 1), newUser));
        return this.getUser(String.valueOf(this.userMap.size()));
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(userMap.get(id));
    }

    private User getUser(String id) {
        return userMap.get(id);
    }

    @Override
    public List<User> all() {
        return new ArrayList<>(userMap.values());
    }

    @Override
    public void deleteById(String id) {
        userMap.remove(id);
    }

    @Override
    public User update(UserDTO userUpdates, String userId) {
        getUser(userId).update(userUpdates);
        return getUser(userId);
    }
}
