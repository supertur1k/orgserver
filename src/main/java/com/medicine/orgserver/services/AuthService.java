package com.medicine.orgserver.services;

import com.medicine.orgserver.dto.JwtRequest;
import com.medicine.orgserver.dto.JwtResponse;
import com.medicine.orgserver.dto.RegUserDto;
import com.medicine.orgserver.dto.UserDto;
import com.medicine.orgserver.entities.User;
import com.medicine.orgserver.exceptions.AppError;
import com.medicine.orgserver.utils.JwtTokensUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokensUtils jwtTokensUtils;
    private final AuthenticationManager authenticationManager;

    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new AppError(HttpStatus.UNAUTHORIZED.value(), "Логин или пароль введены не верно . . . "), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokensUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token,  userService.getIdByUsername(jwtRequest.getUsername())));
    }

    public ResponseEntity<?> createNewUser(@RequestBody RegUserDto registrationUserDto) {
        if (registrationUserDto.getEmail().isBlank() || registrationUserDto.getUsername().isBlank()
            || registrationUserDto.getPassword().isBlank() || registrationUserDto.getConfirmPassword().isBlank()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Не все поля заполнены"), HttpStatus.BAD_REQUEST);
        }
        if (!registrationUserDto.getPassword().equals(registrationUserDto.getConfirmPassword())) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пароли не совпадают"), HttpStatus.BAD_REQUEST);
        }
        if (userService.findByUsername(registrationUserDto.getUsername()).isPresent()) {
            return new ResponseEntity<>(new AppError(HttpStatus.BAD_REQUEST.value(), "Пользователь с указанным именем уже существует"), HttpStatus.BAD_REQUEST);
        }

        User user = userService.createNewUser(registrationUserDto);
        return ResponseEntity.ok(new UserDto(user.getId(), user.getUsername(), user.getEmail()));
    }
}
