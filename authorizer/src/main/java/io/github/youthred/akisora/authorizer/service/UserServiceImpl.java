package io.github.youthred.akisora.authorizer.service;

import io.github.youthred.akisora.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final PasswordEncoder passwordEncoder;

    private List<User> users;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        users = new ArrayList<>(1);
        users.add(
                new User().setId(123L).setUsername("admin").setPassword(this.passwordEncoder.encode("123456")).setStatus(1).setRoles(Collections.singletonList("ADMIN"))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        passwordEncoder.encode("123");
        return null;
    }
}
