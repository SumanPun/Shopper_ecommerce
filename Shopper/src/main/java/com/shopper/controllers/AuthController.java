package com.shopper.controllers;

import com.shopper.dto.*;
import com.shopper.entites.User;
import com.shopper.repository.UserRepository;
import com.shopper.services.auth.AuthService;
import com.shopper.security.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin("http://localhost:4200")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtTokenHelper;
    private final AuthService authService;
    private final UserRepository  userRepository;


    @PostMapping("/authenticate")
    public  ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException, JSONException {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect username or password");
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        String jwt = jwtTokenHelper.generateToken(userDetails.getUsername());
        String refreshToken = jwtTokenHelper.generateRefreshToken(userDetails.getUsername());
        Optional<User> user = userRepository.findFirstByEmail(authenticationRequest.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if(user.isPresent()) {
            authenticationResponse.setJwtToken(jwt);
            authenticationResponse.setJwtRefreshToken(refreshToken);
            authenticationResponse.setUserId(user.get().getId());
            authenticationResponse.setUserRole(user.get().getRole());
        }
        return new ResponseEntity<>(authenticationResponse,HttpStatus.OK);
    }
    @GetMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        AuthenticationResponse response = authService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/sign-up/user")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/sign-up/admin")
    public ResponseEntity<?> signupAdmin(@RequestBody SignupRequest signupRequest) {
        if (authService.hasUserWithEmail(signupRequest.getEmail())) {
            return new ResponseEntity<>("User already exists", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDto userDto = authService.createUser(signupRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }
}
