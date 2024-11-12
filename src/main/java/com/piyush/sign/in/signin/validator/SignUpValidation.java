package com.piyush.sign.in.signin.validator;

import com.piyush.sign.in.signin.entity.UserEntity;
import com.piyush.sign.in.signin.repository.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SignUpValidation {

    private static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{9,}$";

    private final UserRepo userRepo;

    public SignUpValidation(final UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public String validate(final UserEntity user) {
        String validationResult = "";
        if (StringUtils.isBlank(user.getUserId())) {
            validationResult += "User Id cannot be blank.\n";
        }
        if (StringUtils.isBlank(user.getUserName())) {
            validationResult += "User Name cannot be blank.\n";
        }
        if (StringUtils.isBlank(user.getUserPassword())) {
            validationResult += "Password cannot be blank.\n";
        } else if (!user.getUserPassword().matches(PASSWORD_REGEX)) {
            log.info("{} doesn't matches regex!", user.getUserPassword());
            validationResult += "Password is invalid. It must contains one lower case, " +
                    "one upper case, a special character and length must be greater than or equal to 8. ";
        }
        if (userRepo.findById(user.getUserId()).isPresent()) {
            validationResult += "User with this userId is already present, please try another user id. ";
        }

        return validationResult;
    }
}
