package com.senla.hotelsecurity.dto;

import com.senla.hotel.enums.Role;
import com.senla.hotel.validator.annotation.EnumValidator;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
    @NotNull
    @Size(max = 255)
    private String login;

    @NotNull
    @Size(max = 255)
    private String password;

    @EnumValidator(targetClassType = Role.class)
    private String role;
}
