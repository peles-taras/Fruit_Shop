package com.fruit.shop.DTO;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}
