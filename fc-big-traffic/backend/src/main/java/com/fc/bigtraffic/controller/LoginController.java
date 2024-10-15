package com.fc.bigtraffic.controller;

import com.fc.bigtraffic.controller.dto.LoginRequest;
import com.fc.bigtraffic.service.UserService;
import com.fc.bigtraffic.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {

    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        userService.checkUser(request.email(), request.password());

        return jwtService.createToken(request.email());
    }

    @GetMapping("/token/validation")
    public ResponseEntity<Void> validateToken(@RequestParam("token") String token) {
        if (jwtService.validate(token)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
