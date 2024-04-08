package com.shopper.security;

import com.shopper.entites.User;
import com.shopper.exceptions.ResourceNotFoundException;
import com.shopper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //loading user from database by username
        return this.userRepository.findFirstByEmail(username).orElseThrow(()-> new ResourceNotFoundException("user","email: "+username,0));
    }
}
