package io.github.lucasiferreira.produtosapi.controller;

import io.github.lucasiferreira.produtosapi.dto.UserRequest;
import io.github.lucasiferreira.produtosapi.dto.UserResponse;
import io.github.lucasiferreira.produtosapi.security.JwtService;
import io.github.lucasiferreira.produtosapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("login")
    public ResponseEntity<String> login(@Valid @RequestBody UserRequest userRequest) {
        Authentication authentication = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                userRequest.username(),
                                userRequest.password()
                        ));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(token);
    }

    @PostMapping("register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody UserRequest userRequest) {
        UserResponse response = userService.register(userRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
