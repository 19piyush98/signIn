package com.piyush.sign.in.signin.model;

import lombok.Data;

@Data
public class SignUpRequest {

    private String userId;

    private String userName;

    private String userInfo;

    private String password;
}
