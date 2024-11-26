package com.wellnest.wellnest.Controller;

import com.wellnest.wellnest.Models.Request.LoginRequest;
import com.wellnest.wellnest.Models.Request.RegisterRequest;
import com.wellnest.wellnest.Models.Responses.AuthReponse;
import com.wellnest.wellnest.Services.AuthService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthReponse> login(@RequestBody @Valid LoginRequest loginRequest)
    {
        return ResponseEntity.ok(authService.login(loginRequest));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthReponse> register(@RequestBody @Valid RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping(value = "hola")
    public ResponseEntity<String> hola(@RequestBody  String holaRequest){
        return ResponseEntity.ok("Hola");
    }



}
