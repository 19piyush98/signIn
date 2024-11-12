package com.piyush.sign.in.signin.controller;

import com.piyush.sign.in.signin.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import com.piyush.sign.in.signin.model.SignInRequest;
import com.piyush.sign.in.signin.model.SignInResponse;
import com.piyush.sign.in.signin.model.SignUpRequest;
import com.piyush.sign.in.signin.model.SignUpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.piyush.sign.in.signin.service.UserService;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody final SignInRequest request) {
        final Optional<UserEntity> user = userService.getUser(request.getUserId());
        if (user.isPresent() && user.get().getUserPassword().equals(request.getPassword())) {
            final String sessionToken = UUID.randomUUID().toString();
            final SignInResponse response = SignInResponse.builder()
                    .userId(user.get().getUserId())
                    .isValidUser(true)
                    .userInfo(user.get().getUserInfo())
                    .sessionToken(sessionToken)
                    .build();
            return ResponseEntity.ok(response);
        } else {
            final SignInResponse response = SignInResponse.builder()
                    .isValidUser(false)
                    .build();
            return ResponseEntity.ok(response);
        }
    }

    @PostMapping("/signUp")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody final SignUpRequest request) {
        log.info("Request is {}, {}", request.getUserId(), request.getUserInfo());
        UserEntity userEntity = UserEntity.builder()
                .userId(request.getUserId())
                .userName(request.getUserName())
                .userPassword(request.getPassword())
                .userInfo(request.getUserInfo())
                .build();
        Optional<String> validationResult = userService.addUser(userEntity);
        if (!validationResult.isPresent()) {
            final SignUpResponse response = SignUpResponse.builder()
                    .message("Successfully signedUp!")
                    .build();
            return ResponseEntity.ok(response);
        } else {
            final SignUpResponse response = SignUpResponse.builder()
                    .message(validationResult.get())
                    .build();
            return ResponseEntity.ok(response);
        }
    }
}
