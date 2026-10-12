
package com.gymgo.controller;

import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.gymgo.model.AppUser;
import com.gymgo.repository.AppUserRepository;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(
            AppUserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(
            @RequestBody Map<String, String> request) {

        String name = request.getOrDefault("fullName", "").trim();
        String email = request.getOrDefault("email", "")
                .trim().toLowerCase();
        String password = request.getOrDefault("password", "");

        if (name.isEmpty() || email.isEmpty()
                || password.length() < 8
                || password.length() > 72) {
            return result(HttpStatus.BAD_REQUEST,
                    "Enter your name and email and a password of 8–72 characters.");
        }

        if (userRepository.existsByEmail(email)) {
            return result(HttpStatus.CONFLICT,
                    "This email is already registered. Please sign in.");
        }

        AppUser user = new AppUser();
        user.setFullName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);

        return result(HttpStatus.CREATED,
                "Registration successful. Please sign in.");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody Map<String, String> request,
            HttpServletRequest servletRequest) {

        String email = request.getOrDefault("email", "")
                .trim().toLowerCase();
        String password = request.getOrDefault("password", "");

        var found = userRepository.findByEmail(email);

        if (found.isEmpty()
                || !passwordEncoder.matches(
                        password, found.get().getPassword())) {
            return result(HttpStatus.UNAUTHORIZED,
                    "Invalid email or password.");
        }

        AppUser user = found.get();
        HttpSession session = servletRequest.getSession(true);

        session.setAttribute("userId", user.getId());
        session.setAttribute("userEmail", user.getEmail());
        session.setAttribute("userName", user.getFullName());

        Map<String, Object> body = new HashMap<>();
        body.put("message", "Login successful.");
        body.put("fullName", user.getFullName());

        return ResponseEntity.ok(body);
    }

    private ResponseEntity<Map<String, Object>> result(
            HttpStatus status, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("message", message);
        return ResponseEntity.status(status).body(body);
    }
}
