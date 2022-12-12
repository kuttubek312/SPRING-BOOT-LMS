package com.peaksoft.springbootlms.controller;

import com.peaksoft.springbootlms.config.jwt.JwtTokenUtil;
import com.peaksoft.springbootlms.dto.*;
import com.peaksoft.springbootlms.model.entity.User;
import com.peaksoft.springbootlms.repository.UserRepository;
import com.peaksoft.springbootlms.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/jwt")
@Tag(name = "Auth API",
        description = "User with role admin, editor can login and registration ")
public class AuthController {

    private final UserService userService;
    private final UserRepository repository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LoginMapper loginMapper;

    @PostMapping("login")
    @Operation(summary = "login", description = "we can login")
    public ResponseEntity<LoginResponse> getLogin(@RequestBody RegisterRequest request) {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
            User user = repository.findByEmail(token.getName()).get();
            return ResponseEntity.ok()
                    .body(loginMapper.loginView(jwtTokenUtil.generateToken(user), ValidationExceptionType.SUCCESSFUL, user));
        } catch (BadCredentialsException exception) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(loginMapper.loginView("",
                    ValidationExceptionType.LOGIN_FAILED, null));
        }
    }

    @PostMapping("registration")
    @Operation(summary = "registration", description = "we can registration")
    public RegisterResponse create(@RequestBody RegisterRequest request) {
        return userService.create(request);
    }
}
