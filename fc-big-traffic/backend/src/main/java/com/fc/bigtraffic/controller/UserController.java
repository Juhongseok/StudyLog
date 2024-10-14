package com.fc.bigtraffic.controller;

import com.fc.bigtraffic.controller.dto.CreateUserRequest;
import com.fc.bigtraffic.controller.dto.IdResponse;
import com.fc.bigtraffic.service.UserService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public IdResponse createUser(@RequestBody CreateUserRequest request) {
        Long createdUserId = userService.save(request.username(), request.password(), request.email());

        return new IdResponse(createdUserId);
    }

    @DeleteMapping("/{userId}")
    public void removeUser(
            @Parameter(description = "ID of the user to be deleted",required = true)
            @PathVariable("userId") Long userId
    ) {
        userService.delete(userId);
    }

}
