package com.fc.bigtraffic.service;

import com.fc.bigtraffic.entity.User;
import com.fc.bigtraffic.repository.UserRepository;
import com.fc.bigtraffic.service.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public Long save(String username, String password, String email) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword, email);

        return userRepository.save(user).getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<UserInfo> getAll() {
        return userRepository.findAll().stream()
                .map(user -> new UserInfo(user.getId(), user.getUsername(), user.getEmail()))
                .toList();
    }

    public void checkUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("User with email " + email + " does not exist"));

        passwordEncoder.matches(password, user.getPassword());
    }

}
