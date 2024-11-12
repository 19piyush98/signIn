package com.piyush.sign.in.signin.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SignInResponse {

    private boolean isValidUser;

    private String userId;

    private String userInfo;

    private String userName;

    private String sessionToken;
}
