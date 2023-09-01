package org.ieti.proyecto.service.user;

import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User save(User newUser);
    Optional<User> findById(String id);
    List<User> all();
    void deleteById(String id);
    User update(UserDTO userUpdates, String userId);
}
