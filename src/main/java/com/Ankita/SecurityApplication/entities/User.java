package com.Ankita.SecurityApplication.entities;

import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique=true)
    private String email;

    private String password;

    private String name;

//    @ElementCollection(fetch= FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    private Set<Role> roles;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public  String getPassword() {

        return this.password;
    }

    @Override
    public String getUsername() {

        return this.email;
    }
}
