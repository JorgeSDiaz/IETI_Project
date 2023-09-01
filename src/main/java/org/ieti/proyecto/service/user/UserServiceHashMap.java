package org.ieti.proyecto.service.user;

import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceHashMap implements UserService {
    private final Map<String, User> userMap;

    private UserServiceHashMap() {
        this.userMap = new HashMap<>();
    }
    @Override
    public User save(User newUser) {
        return this.userMap.put(String.valueOf(this.userMap.size() + 1), newUser);
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
        return (List<User>) userMap.values();
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
