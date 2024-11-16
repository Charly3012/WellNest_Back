package com.wellnest.wellnest.Services;

import com.wellnest.wellnest.Models.Request.LoginRequest;
import com.wellnest.wellnest.Models.Request.RegisterRequest;
import com.wellnest.wellnest.Models.Responses.AuthReponse;
import com.wellnest.wellnest.Models.User;
import com.wellnest.wellnest.Models.UserRole;
import com.wellnest.wellnest.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthReponse login(LoginRequest loginRequest)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        String token = jwtService.getToken(user);
        return AuthReponse.builder()
                .token(token)
                .build();
    }

    public AuthReponse register(RegisterRequest request)
    {


        User user = User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .bornDate(request.getBornDate())
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        return AuthReponse.builder()
                .token(jwtService.getToken(user))
                .build();
    }

}
