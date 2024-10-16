package com.fc.bigtraffic.controller;

import com.fc.bigtraffic.controller.dto.LoginRequest;
import com.fc.bigtraffic.service.UserService;
import com.fc.bigtraffic.jwt.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
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
    public void login(@RequestBody LoginRequest request, HttpServletResponse response) {
        userService.checkUser(request.email(), request.password());

        String token = jwtService.createToken(request.email());
        setTokenInCookie(token, 3600, response);
    }

    @PostMapping("/v1/logout")
    public void logout(HttpServletResponse response) {
        setTokenInCookie(null, 0, response);
    }

    @PostMapping("/v2/logout")
    public void blacklistLogout(@CookieValue(required = false, name = "Authorization") String token, HttpServletResponse response) {
        jwtService.blacklistToken(token);
        setTokenInCookie(null, 0, response);
    }

    @PostMapping("/v2/logout/all")
    public void logoutAll(@CookieValue(required = false, name = "Authorization") String token, HttpServletResponse response) {
        jwtService.blacklistTokenAll(token);
        setTokenInCookie(null, 0, response);
    }

    private void setTokenInCookie(String token, int maxAge, HttpServletResponse response) {
        Cookie cookie = new Cookie("Authorization", token);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);

        response.addCookie(cookie);
    }

    @GetMapping("/token/validation")
    public ResponseEntity<Void> validateToken(@RequestParam("token") String token) {
        if (jwtService.validate(token)) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.badRequest().build();
    }

}
