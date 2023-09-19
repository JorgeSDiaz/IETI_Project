package org.ieti.proyecto.models.users;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;

@Document
public class User {
    @Id
    private String id;
    private Date createdAt;
    private String name, lastname,email, passwordHash;

    public User() {
    }

    public User(String id, String name, String lastname, String email, String password) {
        this.passwordHash = new BCryptPasswordEncoder().encode(password);
        this.createdAt = new Date();
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
    }

    public User(String id, UserDTO userDto) {
        this(id, userDto.getName(), userDto.getLastname(), userDto.getEmail(), userDto.getPassword());
    }

    public User(UserDTO userDTO) {
        this(null, userDTO.getName(), userDTO.getLastname(), userDTO.getEmail(), userDTO.getPassword());
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

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public void update(UserDTO userUpdates){
        if (!userUpdates.getName().isEmpty()) {
            this.setName(userUpdates.getName());
        }

        if (!userUpdates.getLastname().isEmpty()) {
            this.setLastname(userUpdates.getLastname());
        }

        if (!userUpdates.getEmail().isEmpty()) {
            this.setEmail(userUpdates.getEmail());
        }

        if (!userUpdates.getPassword().isEmpty()){
            this.setPasswordHash(new BCryptPasswordEncoder().encode(userUpdates.getPassword()));
        }
    }
}
