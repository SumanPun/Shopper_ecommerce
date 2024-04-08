package com.shopper.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwtToken;
    private String jwtRefreshToken;
}
