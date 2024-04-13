package com.shopper.dto;

import com.shopper.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwtToken;
    private String jwtRefreshToken;
    private Long userId;
    private UserRole userRole;
}
