package com.medicine.orgserver.controllers;

import com.medicine.orgserver.dto.JwtRequest;
import com.medicine.orgserver.dto.RegUserDto;
import com.medicine.orgserver.services.AuthService;
import com.medicine.orgserver.services.UserService;
import com.medicine.orgserver.utils.JwtTokensUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Tag(name = "Класс с контроллерами для авторизации на сервере MedicineOrganizer")
public class AuthController {

    private final UserService userService;
    private final JwtTokensUtils jwtTokensUtils;
    private final AuthenticationManager authenticationManager;
    private final AuthService authService;

    @Operation(
            summary = "Возвращает токен по переданному логину и паролю",
            description = "Время жизни токена: ${JWT.lifetime}"
    )
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest jwtRequest) {
        return authService.createAuthToken(jwtRequest);
    }

    @Operation(
            summary = "Сохраняет юзера в репозиторий, возвращает UserDto",
            description = "Время жизни токена: ${JWT.lifetime}"
    )
    @PostMapping("/registration")
    public ResponseEntity<?> createNewUser(@RequestBody RegUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}
