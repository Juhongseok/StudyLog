package com.fc.bigtraffic.controller.dto;

public record CreateUserRequest(
        String username,
        String password,
        String email
) {

}
