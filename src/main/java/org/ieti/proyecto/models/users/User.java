package org.ieti.proyecto.models.users;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

public class User {
    private final String id;
    private final Date createdAt;
    private String name, lastname,email, passwordHash;

    public User(String id, Date createdAt, String name, String lastname, String email, String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(UserDTO userUpdates){
        this.setName(userUpdates.getName());
        this.setLastname(userUpdates.getLastname());
        this.setEmail(userUpdates.getEmail());
        if (!userUpdates.getPassword().isEmpty()){
            this.setPasswordHash(new BCryptPasswordEncoder().encode(userUpdates.getPassword()));
        }
    }
}
