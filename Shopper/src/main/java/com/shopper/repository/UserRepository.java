package com.shopper.repository;

import com.shopper.entites.User;
import com.shopper.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.email=?1")
    Optional<User> findFirstByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.role=?1")
    User findByRole(UserRole userRole);

}
