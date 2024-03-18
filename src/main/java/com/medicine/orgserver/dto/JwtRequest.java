package com.medicine.orgserver.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Schema(description = "Сущность JWT-запроса")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtRequest {
    @Schema(description = "Логин юзера")
    String username;
    @Schema(description = "Пароль юзера")
    String password;
}