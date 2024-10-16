package com.fc.bigtraffic.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@NoArgsConstructor
public class JwtBlacklist {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "jwt_blacklist_id")
    private Long id;

    private String token;
    private LocalDateTime expirationTime;
    private String email;

    public JwtBlacklist(String token, LocalDateTime expirationTime, String email) {
        this.token = token;
        this.expirationTime = expirationTime;
        this.email = email;
    }

    public boolean isExpired(LocalDateTime expirationTime) {
        return this.expirationTime.isAfter(expirationTime);
    }

}
