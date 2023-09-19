package org.ieti.proyecto.service.user;

import org.ieti.proyecto.models.users.User;
import org.ieti.proyecto.models.users.UserDTO;
import org.ieti.proyecto.repository.UserMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceMongoDB implements UserService {
    
    private final UserMongoRepository mongoRepository;

    @Autowired
    public UserServiceMongoDB(UserMongoRepository repository) {
        this.mongoRepository = repository;
    }

    @Override
    public User save(UserDTO newUser) {
        return this.mongoRepository.save(new User(newUser));
    }

    @Override
    public Optional<User> findById(String id) {
        return this.mongoRepository.findById(id);
    }

    @Override
    public List<User> all() {
        return this.mongoRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        this.mongoRepository.deleteById(id);
    }

    @Override
    public User update(UserDTO userUpdates, String userId) {
        User existedUser = this.findById(userId).orElse(null);

        if (existedUser == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,  "user with id " + userId + " doesn't exist");
        }

        existedUser.update(userUpdates);

        return this.mongoRepository.save(existedUser);
    }
}
