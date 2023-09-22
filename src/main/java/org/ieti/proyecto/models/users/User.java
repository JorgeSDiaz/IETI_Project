package org.ieti.proyecto.models.users;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Document
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private String id;
    private Date createdAt;
    private String name, lastname,email, password;

    public User(String id, UserDTO newUser) {
        this.id = id;
    }

    public User(UserDTO newUser) {
        this.name = newUser.getName();
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
            this.setPassword(new BCryptPasswordEncoder().encode(userUpdates.getPassword()));
        }
    }

    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
