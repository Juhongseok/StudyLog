package com.fc.bigtraffic.service;

import com.fc.bigtraffic.entity.User;
import com.fc.bigtraffic.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Long save(String username, String password, String email) {
        User user = new User(username, password, email);

        return userRepository.save(user).getId();
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
