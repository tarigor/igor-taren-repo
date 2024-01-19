package com.senla.adssecurity.dto;

import com.senla.adsservice.enums.UserRole;
import com.senla.adsservice.validator.annotation.EnumValidator;
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

    @EnumValidator(targetClassType = UserRole.class)
    private String role;
}
