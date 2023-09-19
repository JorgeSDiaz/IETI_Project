package org.ieti.proyecto.models.users;

import java.util.Date;

public class UserDTO {
    private final String name;
    private final String lastname;
    private final String email;
    private final String password;

    public UserDTO() {
        this.name = "";
        this.lastname = "";
        this.email = "";
        this.password = "";
    }

    public UserDTO(String name, String lastname, String email, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String name, String lastname, String email) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.password = "";
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

    public String getPassword() {
        return password;
    }
}
