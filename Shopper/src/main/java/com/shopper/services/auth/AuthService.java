package com.shopper.services.auth;

import com.shopper.dto.AuthenticationResponse;
import com.shopper.dto.RefreshTokenRequest;
import com.shopper.dto.SignupRequest;
import com.shopper.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
    Boolean hasUserWithEmail(String email);
    AuthenticationResponse refreshToken(RefreshTokenRequest token);
}
