package com.senla.adsservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {
    @NotEmpty
    @Size(min = 3, max = 255, message = "`login` must be between {min} and {max} characters long")
    private String login;

    @NotEmpty
    @Size(min = 5, max = 20, message = "`password` must be between {min} and {max} characters long")
    private String password;
}
