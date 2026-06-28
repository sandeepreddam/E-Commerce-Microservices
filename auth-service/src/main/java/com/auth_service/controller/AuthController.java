package com.auth_service.controller;

import com.auth_service.dto.APIResponse;
import com.auth_service.dto.LoginDto;
import com.auth_service.dto.UserDto;
import com.auth_service.entity.User;
import com.auth_service.repository.UserRepository;
import com.auth_service.service.JWTService;
import com.auth_service.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private UserRepository userRepository;
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    public AuthController(
            UserRepository userRepository,
            UserService userService,
            AuthenticationManager authenticationManager,
            JWTService jwtService) {

        this.userRepository = userRepository;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/customer_signup")
    public ResponseEntity<APIResponse<String>> customerSignUp(
            @RequestBody UserDto userDto){


        APIResponse<String> response = new APIResponse();

        if (userRepository.existsByEmail(
                userDto.getEmail())
        ){
            response.setMessage("Error");
            response.setStatus(500);
            response.setData("Email id already exists");
            return new ResponseEntity<>(
                    response, HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        if (userRepository.existsByUsername(
                userDto.getUsername())){
            response.setMessage("Error");
            response.setStatus(500);
            response.setData("Username already exists");
            return new ResponseEntity<>(
                    response, HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
        userDto.setRole("ROLE_CUSTOMER");
        userService.addUser(userDto);
        response.setMessage("Done");
        response.setStatus(201);
        response.setData("Registration Successfull ");
        return new ResponseEntity<>(
                response, HttpStatus.CREATED) ;
    }

    @PostMapping("/store_signup")
    public ResponseEntity<APIResponse<String>> storeSignUp(
            @RequestBody UserDto userDto
    ){
        APIResponse<String> response = new APIResponse();

        if (userRepository.existsByEmail(
                userDto.getEmail())
        ){
            response.setMessage("Error");
            response.setStatus(500);
            response.setData("Email id already exists");
            return new ResponseEntity<>(
                    response, HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        if (userRepository.existsByUsername(
                userDto.getUsername())
        ){
            response.setMessage("Error");
            response.setStatus(500);
            response.setData("Username already exists");
            return new ResponseEntity<>(
                    response, HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

        userDto.setRole("ROLE_STORE");
        userService.addUser(userDto);
        response.setMessage("Done");
        response.setStatus(201);
        response.setData("Registration Successfull ");
        return new ResponseEntity<>(
                response, HttpStatus.CREATED) ;
    }

    @PostMapping("/login")
    // will give this to authentication manager
    public ResponseEntity<APIResponse<String>> login(
            @RequestBody LoginDto loginDto
    ){

        APIResponse<String> response = new APIResponse<>();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()
                );

        Authentication authentication =
                authenticationManager.authenticate(
                        authenticationToken
                );

        if (authentication.isAuthenticated()){ // from this line i am calling service layer
            User user =
                    userRepository.findByUsername(
                            loginDto.getUsername());

            String token =
                    jwtService.generatedToken(
                            user.getUsername(),
                            user.getRole());
            response.setMessage("Login successful");
            response.setStatus(200);
            response.setData(token);
            return new ResponseEntity<>(
                    response, HttpStatusCode.valueOf(
                            response.getStatus())
            );
        }
        response.setMessage("failed");
        response.setStatus(401);
        response.setData("Un-Authorized Access");
        return new ResponseEntity<>(
                response, HttpStatusCode.valueOf(
                        response.getStatus())
        );
    }
}
