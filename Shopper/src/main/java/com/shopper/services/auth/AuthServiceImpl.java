package com.shopper.services.auth;

import com.shopper.dto.AuthenticationResponse;
import com.shopper.dto.RefreshTokenRequest;
import com.shopper.dto.SignupRequest;
import com.shopper.dto.UserDto;
import com.shopper.entites.User;
import com.shopper.enums.UserRole;
import com.shopper.repository.UserRepository;
import com.shopper.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtUtil jwtTokenHelper;

    public UserDto createUser(SignupRequest signupRequest) {
        User user = new User();

        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        userDto.setEmail(createdUser.getEmail());
        userDto.setName(createdUser.getName());
        userDto.setUserRole(createdUser.getRole());
        return userDto;

    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest token) {
        String userName = jwtTokenHelper.extractUsername(token.getJwtToken());
        User user = userRepository.findFirstByEmail(userName).orElseThrow(()-> new RuntimeException("Invalid Email"));
        if(jwtTokenHelper.validateToken(token.getJwtToken(),user)) {
            String jwt = jwtTokenHelper.generateToken(user.getEmail());
            AuthenticationResponse authenticationResponse = new AuthenticationResponse();
            authenticationResponse.setJwtToken(jwt);
            authenticationResponse.setJwtRefreshToken(token.getJwtToken());
            return authenticationResponse;
        }
        return null;
    }

//    @PostConstruct
//    public void createAdmin() {
//
//        User adminAccount = userRepository.findByRole(UserRole.ADMIN);
//
//        if(adminAccount == null) {
//            User user = new User();
//            user.setEmail("admin@test.com");
//            user.setName("admin");
//            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
//            user.setRole(UserRole.ADMIN);
//            userRepository.save(user);
//        }
//    }

    public Boolean hasUserWithEmail(String email) {
        return userRepository.findFirstByEmail(email).isPresent();
    }

}
