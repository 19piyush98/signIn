package com.piyush.sign.in.signin.service;

import com.piyush.sign.in.signin.entity.UserEntity;
import com.piyush.sign.in.signin.validator.SignUpValidation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.piyush.sign.in.signin.repository.UserRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final SignUpValidation signUpValidation;

    public Optional<UserEntity> getUser(final String userId){
        return userRepo.findById(userId);
    }

    public Optional<String> addUser(final UserEntity user) {
        final String validationResult = signUpValidation.validate(user);
        if (StringUtils.isBlank(validationResult)) {
            userRepo.save(user);
            return Optional.empty();
        }
        return Optional.ofNullable(validationResult);
    }
}
