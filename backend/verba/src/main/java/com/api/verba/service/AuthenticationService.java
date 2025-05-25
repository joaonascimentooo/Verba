package com.api.verba.service;

import com.api.verba.dto.auth.SignUpRequest;
import com.api.verba.dto.auth.LoginResponse;
import com.api.verba.dto.auth.RegisterResponse;
import com.api.verba.dto.auth.SignInRequest;
import com.api.verba.enumm.Role;
import com.api.verba.model.User;
import com.api.verba.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse signup(SignUpRequest request) {
        var user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return RegisterResponse.builder().token(jwt).build();
    }

    public LoginResponse signIn(SignInRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));


        User user = (User) authentication.getPrincipal();

        String jwt = jwtService.generateToken(user);

        LoginResponse response = LoginResponse.builder().token(jwt).build();
       response.setName(user.getName());

        return response;
    }
}
